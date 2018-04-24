package br.com.igbeni.tax;


import br.com.igbeni.tax.rounding.RoundingImpl;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

public class ImportedTaxTest {

    @Test
    public void applyTaxAlwaysOnNetPrice() throws Exception {
        ImportedTax tax = new ImportedTax(
                new DefaultTax(10, new RoundingImpl()),
                new DefaultTax(5, new RoundingImpl())
        );

        BigDecimal amount = tax.amount(new BigDecimal("47.50"));

        assertThat(amount).isEqualTo(new BigDecimal("7.15"));
    }
}