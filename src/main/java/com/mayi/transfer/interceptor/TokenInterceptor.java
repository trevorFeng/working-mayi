package com.mayi.transfer.interceptor;


import com.mayi.transfer.exception.BusinessException;
import com.mayi.transfer.exception.ErrorCodes;
import com.mayi.transfer.service.ThirdPartyClientService;
import com.mayi.transfer.utils.JWTUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Resource
    private JWTUtil jwtUtil;

    @Resource
    private ThirdPartyClientService thirdPartyClientService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o){
        String token = request.getHeader("Access-Token");
        if (token == null || "".equals(token)) {
            throw new BusinessException(ErrorCodes.TOKEN_NOT_FOUND);
        }
        Map<String, Object> tokenData = jwtUtil.getJWTData(token);
        if (tokenData == null) {
            throw new BusinessException(ErrorCodes.TOKEN_ERROR);
        }
        String clientId = (String) tokenData.get("clientId");
        if (StringUtils.isNoneBlank(clientId) && thirdPartyClientService.isClientExistByClientId(clientId)) {
            return Boolean.TRUE;
        }
        throw new BusinessException(ErrorCodes.TOKEN_ERROR);
    }
}
