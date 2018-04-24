package br.com.igbeni.utils;

import br.com.igbeni.model.Receipt;
import br.com.igbeni.model.ReceiptItem;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Utils {

    public static String format(Receipt receipt) {
        StringBuilder sb = new StringBuilder();

        for (ReceiptItem item : receipt.getItems())
            sb.append(formatReceiptItem(item));

        sb.append("Sales Taxes: ").append(formatPrice(receipt.getSalesTaxes())).append("\n");
        sb.append("Total: ").append(formatPrice(receipt.getTotal()));

        return sb.toString();
    }

    private static String formatReceiptItem(ReceiptItem item) {
        StringBuilder sb = new StringBuilder();

        sb.append(item.getQuantity()).append(" ");
        if (item.isImported()) {
            sb.append("imported").append(" ");
        }
        sb.append(item.getProductName()).append(": ");
        sb.append(formatPrice(item.getTaxedPrice())).append("\n");

        return sb.toString();
    }

    private static String formatPrice(BigDecimal value) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00#");
        decimalFormat.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.US));
        return decimalFormat.format(value);
    }
}
