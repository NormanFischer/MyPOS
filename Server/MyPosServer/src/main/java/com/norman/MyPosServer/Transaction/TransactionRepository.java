package com.norman.MyPosServer.Transaction;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
    @Query(value = "SELECT transaction.* FROM transaction LEFT JOIN user ON transaction.user_id = user.id WHERE user.username LIKE %?1%", nativeQuery=true)
    List<Transaction> listByUsername(String username);


}
