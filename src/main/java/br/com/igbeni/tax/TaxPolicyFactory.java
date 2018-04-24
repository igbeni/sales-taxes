package br.com.igbeni.tax;

import br.com.igbeni.model.ProductType;
import br.com.igbeni.tax.rounding.RoundingImpl;

public class TaxPolicyFactory {

    private static final DefaultTax IMPORTED_TAX = new DefaultTax(5, new RoundingImpl());
    private static final DefaultTax DEFAULT_TAX = new DefaultTax(10, new RoundingImpl());
    private static final ProductType[] EXEMPT_PRODUCT_TYPES = new ProductType[]{ProductType.BOOKS, ProductType.MEDICALS, ProductType.FOOD};

    public static TaxPolicy create() {
        return new TaxPolicyImpl(DEFAULT_TAX, IMPORTED_TAX, EXEMPT_PRODUCT_TYPES);
    }
}
