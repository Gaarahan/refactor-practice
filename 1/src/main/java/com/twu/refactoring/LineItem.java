package com.twu.refactoring;

public class LineItem {
    private final String desc;
    private final double price;
    private final int quantity;

    public LineItem(String desc, double price, int quantity) {
        super();
        this.desc = desc;
        this.price = price;
        this.quantity = quantity;
    }

    public String getDescription() {
        return desc;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    double totalAmount() {
        return price * quantity;
    }
}