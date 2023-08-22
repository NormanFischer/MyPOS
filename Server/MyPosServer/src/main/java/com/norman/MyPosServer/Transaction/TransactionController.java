package com.norman.MyPosServer.Transaction;

import com.norman.MyPosServer.User.User;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;

@RequestMapping(path="transaction/")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @PostMapping(path="/postTransaction")
    public String postTransaction(@RequestBody PostTransactionDTO postTransactionDTO,
                                  Authentication authentication) {
        if(authentication != null && authentication.isAuthenticated()) {
            User authenticatedUser = (User) authentication.getPrincipal();

        }
        return "Unimplemented";
    }
}
