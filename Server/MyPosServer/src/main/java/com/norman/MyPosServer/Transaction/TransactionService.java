package com.norman.MyPosServer.Transaction;

import com.norman.MyPosServer.User.User;
import com.norman.MyPosServer.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserService userService;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository,
                              UserService userService) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
    }

    public void saveTransaction(User transactionUser, PostTransactionDTO postTransactionDTO) {
        //TODO: Better business logic probably
        Transaction toAdd = new Transaction();
        toAdd.setTransactionItems(postTransactionDTO.getItems());
        toAdd.setUser(transactionUser);
        if (transactionUser != null) {
            transactionRepository.save(toAdd);
        } else {
            System.out.println("User id not found, transaction not saved");
        }
    }

}
