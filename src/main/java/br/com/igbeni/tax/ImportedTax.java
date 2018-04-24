package br.com.igbeni.tax;

import java.math.BigDecimal;
import java.util.Arrays;

public class ImportedTax implements Tax {

    private final Tax[] taxes;

    public ImportedTax(Tax... taxes) {
        this.taxes = taxes;
    }

    @Override
    public BigDecimal amount(BigDecimal productPrice) {
        BigDecimal amount = BigDecimal.ZERO;

        for (Tax tax : taxes) {
            amount = amount.add(tax.amount(productPrice));
        }

        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImportedTax that = (ImportedTax) o;

        return Arrays.equals(taxes, that.taxes);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(taxes);
    }
}
