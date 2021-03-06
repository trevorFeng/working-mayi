package com.mayi.transfer.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.mayi.transfer.dao.crediting.mapper.ReceiveAccountMapper;
import com.mayi.transfer.dao.payment.mapper.PaymentAccountMapper;
import com.mayi.transfer.dao.payment.mapper.TransactionMapper;
import com.mayi.transfer.domain.PaymentAccount;
import com.mayi.transfer.domain.ReceiveAccount;
import com.mayi.transfer.domain.Transaction;
import com.mayi.transfer.exception.BusinessException;
import com.mayi.transfer.exception.ErrorCodes;
import com.mayi.transfer.request.TransferRequest;
import com.mayi.transfer.utils.JWTUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class TransferService {

    @Resource
    private ThirdPartyClientService thirdPartyClientService;

    @Resource
    private TransactionMapper transactionMapper;

    @Resource
    private PaymentAccountMapper paymentAccountMapper;

    @Resource
    private ReceiveAccountMapper receiveAccountMapper;

    @Resource
    private JWTUtil JWTUtil;

    @Transactional(rollbackFor = Exception.class)
    public void transfer(TransferRequest transferRequest) {
        if (transferRequest.getTransferAmount().compareTo(new BigDecimal(0)) <= 0) {
            throw new BusinessException(ErrorCodes.TRANSFER_AMOUNT_ILLEGAL);
        }

        Transaction transaction = new Transaction();
        transaction.setTransactionId(transferRequest.getTransactionId());
        transaction.setAmount(transferRequest.getTransferAmount());
        transaction.setPaymentAccountId(transferRequest.getTransferPeopleId());
        transaction.setReceiveAccountId(transferRequest.getPayeeId());
        transaction.setTransactionTime(System.currentTimeMillis());
        transactionMapper.insert(transaction);

        PaymentAccount paymentAccount = paymentAccountMapper.selectById(transferRequest.getTransferPeopleId());
        if (paymentAccount == null) {
            throw new BusinessException(ErrorCodes.PAYMENT_ACCOUNT_NOT_FOUND);
        }
        if (paymentAccount.getBalance().compareTo(transaction.getAmount()) < 0) {
            throw new BusinessException(ErrorCodes.BALANCE_NOT_ENOUGH);
        }
        UpdateWrapper<PaymentAccount> paymentAccountUpdateWrapper = new UpdateWrapper<>();
        paymentAccountUpdateWrapper.eq("version", paymentAccount.getVersion())
                .eq("id", paymentAccount.getId())
                .set("balance", paymentAccount.getBalance().subtract(transaction.getAmount()))
                .set("version", paymentAccount.getVersion() + 1);
        int paymentUpdateRows = paymentAccountMapper.update(null, paymentAccountUpdateWrapper);
        if (paymentUpdateRows == 0) {
            throw new BusinessException(ErrorCodes.BALANCE_UPDATE_FAILED);
        }

        ReceiveAccount receiveAccount = receiveAccountMapper.selectById(transferRequest.getPayeeId());
        UpdateWrapper<ReceiveAccount> receiveAccountUpdateWrapper = new UpdateWrapper<>();
        receiveAccountUpdateWrapper.eq("version", receiveAccount.getVersion())
                .eq("id", receiveAccount.getId())
                .set("balance", receiveAccount.getBalance().add(transaction.getAmount()))
                .set("version", receiveAccount.getVersion() + 1);
        int receiveUpdateRows = receiveAccountMapper.update(null, receiveAccountUpdateWrapper);
        if (receiveUpdateRows == 0) {
            throw new BusinessException(ErrorCodes.BALANCE_UPDATE_FAILED);
        }

    }

    public String getPaymentVoucher(String clientId){
        if (!thirdPartyClientService.isClientExistByClientId(clientId)) {
            throw new BusinessException(ErrorCodes.CLIENT_ID_NOT_FOUND);
        }
        Map<String, Object> tokenData = new HashMap<>();
        tokenData.put("clientId", clientId);
        return JWTUtil.createJWT(tokenData);
    }
}
