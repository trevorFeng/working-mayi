package com.mayi.transfer.controller;

import com.mayi.transfer.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("token")
public class TokenController {

    @Resource
    private TransferService transferService;

    @GetMapping
    public ResponseEntity<String> getPaymentVoucher(@RequestParam("clientId") String clientId){
        return ResponseEntity.ok(transferService.getPaymentVoucher(clientId));
    }
}
