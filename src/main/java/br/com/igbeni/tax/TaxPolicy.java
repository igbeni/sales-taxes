package br.com.igbeni.tax;

import br.com.igbeni.model.CartItem;

public interface TaxPolicy {

    Tax forItem(CartItem cartItem);
}
