package com.norman.MyPosServer.Transaction;

import com.norman.MyPosServer.User.User;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping(path="/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @PostMapping(path="/postTransaction")
    public String postTransaction(@RequestBody PostTransactionDTO postTransactionDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated()) {
            User authenticatedUser = (User) authentication.getPrincipal();
            transactionService.saveTransaction(authenticatedUser, postTransactionDTO);
            return "Got an authenticated user!";
        }
        return "User authentication failed";
    }

}
