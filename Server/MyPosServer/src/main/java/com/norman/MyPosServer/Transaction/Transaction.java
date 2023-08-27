package com.norman.MyPosServer.Transaction;

import com.norman.MyPosServer.Item.Item;
import com.norman.MyPosServer.User.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @ManyToOne
    private User user;
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="transaction")
    private List<TransactionItem> transactionItems = new ArrayList<>();

    private LocalDateTime dateTime;

    public User getUser() { return this.user; }

    public void setUser(User user) {
        this.user = user;
    }

    public List<TransactionItem> getTransactionItems() { return this.transactionItems; }

    public void setTransactionItems(ArrayList<TransactionItem> transactionItems) { this.transactionItems = transactionItems; }

    public LocalDateTime getDateTime() { return this.dateTime; }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public long getTransactionTotal() {
        System.out.println("Getting total...");
        long runningTotal = 0;
        for(TransactionItem transactionItem : this.transactionItems) {
            runningTotal += transactionItem.getCostPer() * transactionItem.getQuantity();
        }
        return runningTotal;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User: " + user.toString() + "\n");
        sb.append("Items:\n");
        sb.append(transactionItems.toString() + "\n");
        return sb.toString();
    }

}
