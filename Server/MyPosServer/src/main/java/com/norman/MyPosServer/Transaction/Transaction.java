package com.norman.MyPosServer.Transaction;

import com.norman.MyPosServer.Security.Authority;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private int userAuthor;

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="transaction")
    private Set<TransactionItem> transactionItems = new HashSet<>();

}
