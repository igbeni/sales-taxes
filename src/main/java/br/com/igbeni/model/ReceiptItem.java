package br.com.igbeni.model;

import br.com.igbeni.tax.Tax;

import java.math.BigDecimal;

public class ReceiptItem {

    private final BigDecimal taxedPrice;
    private int quantity;
    private String productName;
    private boolean isImported;
    private BigDecimal taxDuty;

    public ReceiptItem(String productName, BigDecimal taxedPrice, BigDecimal taxDuty, int quantity, boolean isImported) {
        this.taxedPrice = taxedPrice;
        this.quantity = quantity;
        this.productName = productName;
        this.isImported = isImported;
        this.taxDuty = taxDuty;
    }

    public static ReceiptItem from(CartItem cartItem, Tax tax) {
        BigDecimal dutyAmount = tax.amount(cartItem.getProductPrice()).multiply(BigDecimal.valueOf(cartItem.getQuantity()));
        BigDecimal taxedAmount = cartItem.getProductPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())).add(dutyAmount).setScale(2);
        return new ReceiptItem(cartItem.getProductName(), taxedAmount, dutyAmount, cartItem.getQuantity(), cartItem.isImported());
    }

    public BigDecimal getTaxedPrice() {
        return taxedPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getProductName() {
        return productName;
    }

    public boolean isImported() {
        return isImported;
    }

    public BigDecimal getTaxDuty() {
        return taxDuty;
    }
}
