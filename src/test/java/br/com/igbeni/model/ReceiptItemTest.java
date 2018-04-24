package br.com.igbeni.model;

import br.com.igbeni.tax.DefaultTax;
import br.com.igbeni.tax.rounding.RoundingImpl;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;


public class ReceiptItemTest {

    private static final BigDecimal TAX_AMOUNT = BigDecimal.ONE;

    @Test
    public void buildFromCartItem() throws Exception {
        CartItem cartItem = new CartItem("product name", new BigDecimal("14.99"), 1, true, ProductType.OTHER);
        ReceiptItem receiptItem = ReceiptItem.from(cartItem, new DefaultTax(10, new RoundingImpl()));

        assertThat(receiptItem.getProductName()).isEqualTo(cartItem.getProductName());
        assertThat(receiptItem.getTaxedPrice()).isEqualTo(new BigDecimal("16.49"));
        assertThat(receiptItem.isImported()).isEqualTo(cartItem.isImported());
        assertThat(receiptItem.getQuantity()).isEqualTo(cartItem.getQuantity());
        assertThat(receiptItem.getTaxDuty()).isEqualTo(new BigDecimal("1.50"));
    }
}