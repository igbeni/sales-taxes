package br.com.igbeni.model;

import br.com.igbeni.tax.TaxPolicyFactory;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ReceiptTest {

    private Receipt receipt;

    @Before
    public void setUp() throws Exception {
        receipt = new Receipt(TaxPolicyFactory.create());
    }

    @Test
    public void emptyReceipt() throws Exception {
        assertThat(receipt.getTotal()).isEqualTo(BigDecimal.ZERO);
        assertThat(receipt.getSalesTaxes()).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    public void addItemsToReceipt() throws Exception {
        receipt.add(new CartItem("product 1", BigDecimal.ONE, 0, false, null));
        receipt.add(new CartItem("product 2", BigDecimal.ONE, 0, false, null));
        receipt.add(new CartItem("product 3", BigDecimal.ONE, 0, false, null));

        List<ReceiptItem> items = receipt.getItems();

        assertThat(items.size()).isEqualTo(3);

        ReceiptItem firstItem = items.get(0);
        ReceiptItem secondItem = items.get(1);
        ReceiptItem thirdItem = items.get(2);

        assertThat(firstItem.getProductName()).isEqualTo("product 1");
        assertThat(secondItem.getProductName()).isEqualTo("product 2");
        assertThat(thirdItem.getProductName()).isEqualTo("product 3");
    }

    @Test
    public void calculatesTotal() throws Exception {
        receipt.add(new CartItem("product 1", new BigDecimal("10.00"), 1, true, ProductType.FOOD));
        receipt.add(new CartItem("product 2", new BigDecimal("47.50"), 1, true, ProductType.OTHER));

        assertThat(receipt.getTotal()).isEqualTo(new BigDecimal("65.15"));
        assertThat(receipt.getSalesTaxes()).isEqualTo(new BigDecimal("7.65"));
    }
}