package com.twu.refactoring;

import java.util.List;

public class Order {
    String name;
    String addr;
    List<LineItem> lineItems;

    public Order(String name, String addr, List<LineItem> lineItems) {
        this.name = name;
        this.addr = addr;
        this.lineItems = lineItems;
    }

    public String getCustomerName() {
        return name;
    }

    public String getCustomerAddress() {
        return addr;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }
}
