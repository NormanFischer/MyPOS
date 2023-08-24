package com.norman.MyPosServer.Transaction;

import com.norman.MyPosServer.Item.Item;
import jakarta.persistence.*;

enum TransactionDirection {CUSTOMER_PURCHASE, CUSTOMER_REFUND}

@Entity
public class TransactionItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private Transaction transaction;

    @ManyToOne
    private Item item;
    private int quantity;
    private TransactionDirection direction;

    public void setItem(Item item) {
        this.item = item;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTransactionDirection(TransactionDirection direction) {
        this.direction = direction;
    }

    public void setTransaction(Transaction transaction) { this.transaction = transaction; }

    public String toString() {
        return item.getItemName();
    }
}
