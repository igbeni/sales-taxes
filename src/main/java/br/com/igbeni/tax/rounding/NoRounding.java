package br.com.igbeni.tax.rounding;

import java.math.BigDecimal;

public class NoRounding extends Rounding {
    @Override
    public BigDecimal round(BigDecimal value) {
        return value;
    }
}
