package com.mayi.transfer.controller;

import com.mayi.transfer.request.TransferRequest;
import com.mayi.transfer.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("transfers")
public class TransferController {

    @Resource
    private TransferService transferService;

    @PutMapping
    public ResponseEntity<Void> transfer(@RequestBody @Valid TransferRequest transferRequest) {
        transferService.transfer(transferRequest);
        return ResponseEntity.ok().build();
    }


 }
