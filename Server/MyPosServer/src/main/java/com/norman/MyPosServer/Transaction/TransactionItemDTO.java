package com.norman.MyPosServer.Transaction;

public class TransactionItemDTO {
    private int quantity;

    private int costPer;
    private String sku;

    public int getQuantity() { return this.quantity; }

    public String getSku() { return this.sku; }

    public int getCostPer() { return this.costPer; }

    public long getSubTotal() { return (long) this.costPer * this.quantity; }

    public String toString() {
        return "Sku: " + sku + ", Quantity: " + quantity + ", Cost Per: " + costPer;
    }
}
