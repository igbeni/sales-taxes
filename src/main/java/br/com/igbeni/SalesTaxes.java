package br.com.igbeni;

import br.com.igbeni.model.CartItem;
import br.com.igbeni.model.Receipt;
import br.com.igbeni.repository.ProductRepository;
import br.com.igbeni.tax.TaxPolicy;
import br.com.igbeni.utils.Utils;

public class SalesTaxes {

    private final ProductRepository repository;
    private Receipt receipt;


    public SalesTaxes(TaxPolicy taxPolicy, ProductRepository repository) {
        this.repository = repository;
        this.receipt = new Receipt(taxPolicy);
    }

    public void addNewItem(String s) {
        receipt.add(CartItem.fromString(s, repository));
    }

    public String completedSale() {
        String result = Utils.format(receipt);

        receipt.clear();

        return result;
    }
}
