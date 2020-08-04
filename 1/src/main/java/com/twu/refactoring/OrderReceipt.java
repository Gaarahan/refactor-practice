package com.twu.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    private final Order order;
    public static final double TAX_PERCENT = .10;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();

        output.append(this.receiptHeaders());

        double totalSalesTax = 0d;
        double total = 0d;
        for (LineItem lineItem : order.getLineItems()) {
            output.append(lineItem.getDescription());
            output.append('\t');
            output.append(lineItem.getPrice());
            output.append('\t');
            output.append(lineItem.getQuantity());
            output.append('\t');
            output.append(lineItem.totalAmount());
            output.append('\n');

            double salesTax = lineItem.totalAmount() * TAX_PERCENT;
            totalSalesTax += salesTax;

            total += lineItem.totalAmount() + salesTax;
        }

        output.append("Sales Tax").append('\t').append(totalSalesTax);

        output.append("Total Amount").append('\t').append(total);
        return output.toString();
    }

    private String receiptHeaders() {
        return "======Printing Orders======\n" + order.getCustomerName() + order.getCustomerAddress();

    }
}