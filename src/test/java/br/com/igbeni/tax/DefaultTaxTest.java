package br.com.igbeni.tax;

import br.com.igbeni.tax.rounding.Rounding;
import br.com.igbeni.tax.rounding.RoundingImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;


public class DefaultTaxTest {

    @Test
    public void dutyOnProductPrice() throws Exception {
        DefaultTax tax = new DefaultTax(15, Rounding.NONE.get());
        BigDecimal amount = tax.amount(new BigDecimal("12.35"));

        assertThat(amount).isEqualTo(new BigDecimal("1.8525"));
    }

    @Test
    public void applyRounding() throws Exception {
        DefaultTax tax = new DefaultTax(5, new RoundingImpl());
        BigDecimal amount = tax.amount(new BigDecimal("0.234"));

        assertThat(amount).isEqualTo(new BigDecimal("0.05"));
    }
}