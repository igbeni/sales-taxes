package br.com.igbeni.tax;

import br.com.igbeni.model.CartItem;
import br.com.igbeni.model.ProductType;

import java.util.Arrays;
import java.util.List;

public class TaxPolicyImpl implements TaxPolicy {

    private final Tax defaultTax;
    private final Tax importedTax;
    private final List<ProductType> exceptProductTypes;

    public TaxPolicyImpl(Tax defaultTax, Tax importedTax, ProductType... exceptProductTypes) {
        this.defaultTax = defaultTax;
        this.importedTax = importedTax;
        this.exceptProductTypes = Arrays.asList(exceptProductTypes);
    }

    @Override
    public Tax forItem(CartItem cartItem) {
        Tax tax = null;

        if (exceptProductTypes.contains(cartItem.getType())) {
            tax = DefaultTax.EXEMPT;
        } else {
            tax = defaultTax;
        }

        if (!cartItem.isImported()) {
            return tax;
        }

        return new ImportedTax(tax, importedTax);
    }
}
