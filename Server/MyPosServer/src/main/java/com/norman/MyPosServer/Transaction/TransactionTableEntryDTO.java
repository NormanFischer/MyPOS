package com.norman.MyPosServer.Transaction;

public class TransactionTableEntryDTO {

    public TransactionTableEntryDTO(String user, String datetime, long total) {
        this.user = user;
        this.datetime = datetime;
        this.total = total;
    }
    private final String user;
    private final String datetime;
    private final long total;

    public String getUser() { return this.user; }

    public String getDatetime() { return this.datetime; }

    public long getTotal() { return this.total; }
}
