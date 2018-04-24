package br.com.igbeni.tax;

import java.math.BigDecimal;

public interface Tax {

    BigDecimal amount(BigDecimal productPrice);
}
