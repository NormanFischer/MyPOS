package com.norman.MyPosServer.Transaction;

import com.norman.MyPosServer.Item.Item;
import com.norman.MyPosServer.User.User;
import jakarta.persistence.*;

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

    public void setUser(User user) {
        this.user = user;
    }

    public void setTransactionItems(ArrayList<TransactionItem> transactionItems) {
        this.transactionItems = transactionItems;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User: " + user.toString() + "\n");
        sb.append("Items:\n");
        sb.append(transactionItems.toString() + "\n");
        return sb.toString();
    }

}
