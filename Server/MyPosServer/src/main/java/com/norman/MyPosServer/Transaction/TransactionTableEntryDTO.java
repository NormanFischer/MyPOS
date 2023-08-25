package com.norman.MyPosServer.Transaction;

public class TransactionTableEntryDTO {

    public TransactionTableEntryDTO(String user, String datetime) {
        this.user = user;
        this.datetime = datetime;
    }
    private String user;
    private String datetime;

    public String getUser() { return this.user; }

    public String getDatetime() { return this.datetime; }
}
