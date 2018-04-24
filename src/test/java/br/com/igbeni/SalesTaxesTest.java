package br.com.igbeni;

import br.com.igbeni.repository.ProductRepository;
import br.com.igbeni.repository.ProductRepositoryFactory;
import br.com.igbeni.tax.TaxPolicyFactory;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.omg.PortableInterceptor.PolicyFactory;

import static org.assertj.core.api.Assertions.*;


public class SalesTaxesTest {

    private SalesTaxes salesTaxes;

    @Before
    public void setUp() {
        salesTaxes = new SalesTaxes(TaxPolicyFactory.create(), ProductRepositoryFactory.create());
    }

    @Test
    public void sellOneTaxFreeNotImportedItem() throws Exception {
        salesTaxes.addNewItem("1 book at 12.49");

        String expected = "1 book: 12.49\n" +
                "Sales Taxes: 0.00\n" +
                "Total: 12.49";

        assertThat(salesTaxes.completedSale()).isEqualTo(expected);
    }

    @Test
    public void sellOneStandardTaxNotImportedItem() throws Exception {
        salesTaxes.addNewItem("1 musical CD at 14.99");

        String expected = "1 musical CD: 16.49\n" +
                "Sales Taxes: 1.50\n" +
                "Total: 16.49";

        assertThat(salesTaxes.completedSale()).isEqualTo(expected);
    }

    @Test
    public void sellMultipleNotImportedItems() throws Exception {
        salesTaxes.addNewItem("1 book at 12.49");
        salesTaxes.addNewItem("1 music CD at 14.99");
        salesTaxes.addNewItem("1 chocolate bar at 0.85");

        String expected = "1 book: 12.49\n" +
                "1 music CD: 16.49\n" +
                "1 chocolate bar: 0.85\n" +
                "Sales Taxes: 1.50\n" +
                "Total: 29.83";

        assertThat(salesTaxes.completedSale()).isEqualTo(expected);
    }

    @Test
    public void sellOneImportedItemWithStandardTax() throws Exception {
        salesTaxes.addNewItem("1 imported bottle of perfume at 47.50");

        String expected = "1 imported bottle of perfume: 54.65\n" +
                "Sales Taxes: 7.15\n" +
                "Total: 54.65";

        assertThat(salesTaxes.completedSale()).isEqualTo(expected);
}

    @Test
    public void sellMultipleImportedItems() throws Exception {
        salesTaxes.addNewItem("1 imported box of chocolates at 10.00");
        salesTaxes.addNewItem("1 imported bottle of perfume at 47.50");

        String expected = "1 imported box of chocolates: 10.50\n" +
                "1 imported bottle of perfume: 54.65\n" +
                "Sales Taxes: 7.65\n" +
                "Total: 65.15";

        assertThat(salesTaxes.completedSale()).isEqualTo(expected);
    }

    @Test
    public void sellMultipleMixedItems() throws Exception {
        salesTaxes.addNewItem("1 imported bottle of perfume at 27.99");
        salesTaxes.addNewItem("1 bottle of perfume at 18.99");
        salesTaxes.addNewItem("1 packet of headache pills at 9.75");
        salesTaxes.addNewItem("1 box of imported chocolates at 11.25");

        String expected = "1 imported bottle of perfume: 32.19\n" +
                "1 bottle of perfume: 20.89\n" +
                "1 packet of headache pills: 9.75\n" +
                "1 imported box of chocolates: 11.85\n" +
                "Sales Taxes: 6.70\n" +
                "Total: 74.68";

        assertThat(salesTaxes.completedSale()).isEqualTo(expected);
    }
}