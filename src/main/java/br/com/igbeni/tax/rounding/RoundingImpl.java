package br.com.igbeni.tax.rounding;

import java.math.BigDecimal;

public class RoundingImpl extends Rounding {
    @Override
    public BigDecimal round(BigDecimal value) {
        double roundedValue = Math.ceil(value.doubleValue() * 20) / 20;
        return new BigDecimal(roundedValue).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }
}
