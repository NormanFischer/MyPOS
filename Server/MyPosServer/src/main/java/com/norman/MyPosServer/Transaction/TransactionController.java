package com.norman.MyPosServer.Transaction;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequestMapping(path="transaction/")
public class TransactionController {
    @PostMapping(path="/postTransaction")
    public String postTransaction(@RequestBody PostTransactionDTO postTransactionDTO) {
        return "Unimplemented";
    }
}
