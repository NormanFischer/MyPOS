package com.norman.MyPosServer.Transaction;

import jakarta.persistence.*;

enum TransactionDirection {CUSTOMER_PURCHASE, CUSTOMER_REFUND}

@Entity
public class TransactionItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private Transaction transaction;

    private int itemId;
    private int quantity;
    private TransactionDirection direction;
}
