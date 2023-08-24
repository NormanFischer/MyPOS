package com.norman.MyPosServer.Transaction;

import java.math.BigInteger;
import java.util.List;

public class PostTransactionDTO {
    private List<TransactionItemDTO> items;

    /*
    We use this to verify that the total we calculated in the terminal software matches the total we calculate
    on the item server. This is so that we do not charge the wrong amount to the customer
     */
    private BigInteger total;

    public List<TransactionItemDTO> getItems() {
        return this.items;
    }

    public BigInteger getTotal() { return this.total; }
}
