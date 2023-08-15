package com.norman.MyPosServer.Item;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String sku;
    private String itemName;
    private int quantity;
    private int cost;

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSku() {
        return this.sku;
    }

    public void setSku(String newSku) {
        this.sku = newSku;
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String newItemName) {
        this.itemName = newItemName;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }

    public int getCost() {
        return this.cost;
    }

    public void setCost(int newCost) {
        this.cost = newCost;
    }

}