package com.mayi.transfer.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class TransferRequest {

    @NotNull(message = "交易人不能为空")
    private Long transferPeopleId;
    @NotNull(message = "接收转账人不能为空")
    private Long payeeId;
    @NotNull(message = "转账金额不能为空")
    private BigDecimal transferAmount;
    @NotBlank(message = "交易号不能为空")
    private String transactionId;
}
