package br.com.igbeni.model;

import br.com.igbeni.tax.Tax;
import br.com.igbeni.tax.TaxPolicy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Receipt {

    private final TaxPolicy taxPolicy;
    private BigDecimal total;
    private BigDecimal salesTaxes;
    private List<ReceiptItem> items;

    public Receipt(TaxPolicy taxPolicy) {
        this.taxPolicy = taxPolicy;
        this.total = BigDecimal.ZERO;
        this.salesTaxes = BigDecimal.ZERO;
        this.items = new ArrayList<>();
    }

    public BigDecimal getTotal() {
        return total;
    }

    public BigDecimal getSalesTaxes() {
        return salesTaxes;
    }

    public List<ReceiptItem> getItems() {
        return items;
    }

    public void add(CartItem cartItem) {
        Tax tax = taxPolicy.forItem(cartItem);

        ReceiptItem receiptItem = ReceiptItem.from(cartItem, tax);
        items.add(receiptItem);

        total = total.add(receiptItem.getTaxedPrice());
        salesTaxes = salesTaxes.add(receiptItem.getTaxDuty());
    }
}
