package br.com.igbeni.tax;

import br.com.igbeni.tax.rounding.Rounding;

import java.math.BigDecimal;

public class DefaultTax implements Tax {
    public static DefaultTax EXEMPT = new DefaultTax(0, Rounding.NONE.get());

    private final int rate;
    private final Rounding rounding;

    public DefaultTax(int rate, Rounding rounding) {
        this.rate = rate;
        this.rounding = rounding;
    }

    @Override
    public BigDecimal amount(BigDecimal productPrice) {
        BigDecimal amount = productPrice.divide(new BigDecimal("100")).multiply(new BigDecimal(rate));
        return rounding.round(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DefaultTax that = (DefaultTax) o;

        return rate == that.rate && rounding.getClass().equals(that.rounding.getClass());
    }

    @Override
    public int hashCode() {
        return rate;
    }
}
