package br.com.igbeni.repository;

import br.com.igbeni.model.ProductType;

import java.util.Map;

public class ProductRepositoryImpl implements ProductRepository {

    private final Map<String, ProductType> productTypeMap;

    public ProductRepositoryImpl(Map<String, ProductType> productTypeMap) {
        this.productTypeMap = productTypeMap;
    }

    @Override
    public ProductType ofProduct(String productName) {
        if (productTypeMap.containsKey(productName)) {
            return productTypeMap.get(productName);
        }

        return null;
    }
}
