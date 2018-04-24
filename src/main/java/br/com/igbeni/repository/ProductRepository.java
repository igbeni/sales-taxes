package br.com.igbeni.repository;

import br.com.igbeni.model.ProductType;

public interface ProductRepository {

    ProductType ofProduct(String productName);
}
