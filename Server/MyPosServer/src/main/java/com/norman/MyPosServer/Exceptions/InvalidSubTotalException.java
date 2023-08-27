package com.norman.MyPosServer.Exceptions;

public class InvalidSubTotalException extends Exception {
    private final long serverSubTotal;
    private final long clientSubTotal;
    public InvalidSubTotalException(long serverSubTotal, long clientSubTotal, String msg) {
        super(msg);
        this.serverSubTotal = serverSubTotal;
        this.clientSubTotal = clientSubTotal;
    }

    public long getServerSubTotal() { return this.serverSubTotal; }
    public long getClientSubTotal() { return this.clientSubTotal; }
}
