package com.norman.MyPosServer.Transaction;

import com.norman.MyPosServer.Exceptions.InvalidFilterException;
import com.norman.MyPosServer.User.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping(path="/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping(path="/getTransactions/{filter}/{query}")
    public ResponseEntity<?> getTransactionsByQuery(@PathVariable String filter, @PathVariable String query) {
        //TODO: Logging
        try {
            List<TransactionTableEntryDTO> transactions = transactionService.getTransactionsByQuery(filter, query);
            System.out.println(transactions);
            return ResponseEntity.ok(transactions);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid filter type requested");
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Null query type requested");
        } catch (InvalidFilterException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid item query type requested");
        }
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
