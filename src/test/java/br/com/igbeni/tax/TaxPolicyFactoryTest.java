package br.com.igbeni.tax;

import br.com.igbeni.model.CartItem;
import br.com.igbeni.model.ProductType;
import br.com.igbeni.tax.rounding.RoundingImpl;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TaxPolicyFactoryTest {

    private TaxPolicy taxPolicy;

    @Before
    public void setUp() {
        taxPolicy = TaxPolicyFactory.create();
    }

    @Test
    public void defaultRateIs10() throws Exception {
        Tax tax = taxPolicy.forItem(notImportedItemWithProductType(ProductType.OTHER));

        assertThat(tax).isEqualTo(new DefaultTax(10, new RoundingImpl()));
    }

    @Test
    public void foodProductIsExempt() throws Exception {
        Tax tax = taxPolicy.forItem(notImportedItemWithProductType(ProductType.FOOD));
        assertThat(tax).isEqualTo(DefaultTax.EXEMPT);
    }

    @Test
    public void bookProductsAreExempt() throws Exception {
        Tax tax = taxPolicy.forItem(notImportedItemWithProductType(ProductType.BOOKS));
        assertThat(tax).isEqualTo(DefaultTax.EXEMPT);
    }

    @Test
    public void medicalProductsAreExempt() throws Exception {
        Tax tax = taxPolicy.forItem(notImportedItemWithProductType(ProductType.MEDICALS));
        assertThat(tax).isEqualTo(DefaultTax.EXEMPT);
    }

    @Test
    public void importedItem() throws Exception {
        Tax tax = taxPolicy.forItem(importedItemWithProductType(ProductType.OTHER));

        Tax expectedTax = new ImportedTax(
                new DefaultTax(10, new RoundingImpl()),
                new DefaultTax(5, new RoundingImpl())
        );
        assertThat(tax).isEqualTo(expectedTax);
    }

    private CartItem importedItemWithProductType(ProductType productType) {
        return new CartItem(null, null, 0, true, productType);
    }

    private CartItem notImportedItemWithProductType(ProductType productType) {
        return new CartItem(null, null, 0, false, productType);
    }
}