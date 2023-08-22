package com.norman.MyPosServer.Transaction;

import java.util.List;

public class PostTransactionDTO {
    private List<TransactionItemDTO> items;

    public List<TransactionItemDTO> getItems() {
        return this.items;
    }
}
