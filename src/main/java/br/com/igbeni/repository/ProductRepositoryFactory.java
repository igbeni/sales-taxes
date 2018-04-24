package br.com.igbeni.repository;

import br.com.igbeni.model.ProductType;

import java.util.HashMap;
import java.util.Map;

import static br.com.igbeni.model.ProductType.*;

public class ProductRepositoryFactory {

    public static ProductRepository create() {
        Map<String, ProductType> productTypeMap = new HashMap<>();
        productTypeMap.put("book", BOOKS);
        productTypeMap.put("music CD", OTHER);
        productTypeMap.put("chocolate bar", FOOD);
        productTypeMap.put("box of chocolates", FOOD);
        productTypeMap.put("bottle of perfume", OTHER);
        productTypeMap.put("packet of headache pills", MEDICALS);
        return new ProductRepositoryImpl(productTypeMap);
    }
}
