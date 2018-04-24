package br.com.igbeni.tax;

import br.com.igbeni.model.ProductType;

import java.math.BigDecimal;

public interface Tax {

    BigDecimal amount(BigDecimal productPrice);
}
