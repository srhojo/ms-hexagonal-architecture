package io.github.srhojo.domain.services;

import io.github.srhojo.domain.models.Product;

import java.util.List;

public interface ProductServices {

    List<Product> retrieveAll();

    Product retrieveById(String id);

    Product create(Product product);

    Product update(String id, Product product);

    void delete(String id);


}
