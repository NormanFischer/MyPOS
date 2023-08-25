package com.norman.MyPosServer.Transaction;

import com.norman.MyPosServer.Item.Item;
import jakarta.persistence.*;

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

    public Item getItem() { return this.item; }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() { return this.quantity; }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public TransactionDirection getDirection() { return this.direction; }

    public void setTransactionDirection(TransactionDirection direction) {
        this.direction = direction;
    }

    public Transaction getTransaction() { return this.transaction; }

    public void setTransaction(Transaction transaction) { this.transaction = transaction; }

    public String toString() {
        return item.getItemName();
    }
}
