package com.norman.MyPosServer.Transaction;

import java.math.BigInteger;
import java.util.List;

public class PostTransactionDTO {
    private List<TransactionItemDTO> items;

    /*
    We use this to verify that the total we calculated in the terminal software matches the total we calculate
    on the item server. This is so that we do not charge the wrong amount to the customer
     */
    private long total;

    public List<TransactionItemDTO> getItems() {
        return this.items;
    }

    public long getTotal() { return this.total; }

    public String toString() {
        return "Items: " + this.items + "\nTotal: " + total;
    }
}
