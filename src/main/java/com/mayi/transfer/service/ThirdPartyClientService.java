package com.mayi.transfer.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mayi.transfer.dao.payment.mapper.ThirdPartyClientMapper;
import com.mayi.transfer.domain.ThirdPartyClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ThirdPartyClientService {

    @Resource
    private ThirdPartyClientMapper thirdPartyClientMapper;

    public Boolean isClientExistByClientId(String clientId) {
        QueryWrapper<ThirdPartyClient> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id")
                .eq("client_id", clientId);
        Integer clientNum = this.thirdPartyClientMapper.selectCount(queryWrapper);
        if (clientNum == 1) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
