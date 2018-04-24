package br.com.igbeni.utils;

import br.com.igbeni.model.CartItem;
import br.com.igbeni.model.Receipt;
import br.com.igbeni.tax.TaxPolicyFactory;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

public class UtilsTest {

    @Test
    public void multipleLinesReceipt() throws Exception {
        Receipt receipt = new Receipt(TaxPolicyFactory.create());
        CartItem firstItem = new CartItem("item1", new BigDecimal("10.02"), 1, true, null);
        receipt.add(firstItem);
        CartItem secondItem = new CartItem("item2", new BigDecimal("2.30"), 1, false, null);
        receipt.add(secondItem);

        String expected = "1 imported item1: 11.62\n" +
                "1 item2: 2.55\n" +
                "Sales Taxes: 1.85\n" +
                "Total: 14.17";

        assertThat(Utils.format(receipt)).isEqualTo(expected);
    }
}