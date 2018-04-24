package br.com.igbeni.tax.rounding;

import java.math.BigDecimal;

public abstract class Rounding {
    public static final ThreadLocal<Rounding> NONE = new ThreadLocal<Rounding>() {
        @Override
        protected Rounding initialValue() {
            return new NoRounding();
        }
    };

    public abstract BigDecimal round(BigDecimal value);
}