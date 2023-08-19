package com.norman.MyPosServer.Transaction;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {}
