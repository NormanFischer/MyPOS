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

    //Cost per is recorded here in case the price changes in the item table
    private long costPer;
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

    public long getCostPer() { return this.costPer; }

    public void setCostPer(long costPer) { this.costPer = costPer; }

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
