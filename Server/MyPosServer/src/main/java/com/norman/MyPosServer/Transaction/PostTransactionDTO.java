package com.norman.MyPosServer.Transaction;

import java.util.List;

public class PostTransactionDTO {
    private List<TransactionItem> items;

    public List<TransactionItem> getItems() {
        return this.items;
    }
}
