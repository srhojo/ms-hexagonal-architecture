package io.github.srhojo.domain.repositories;

import io.github.srhojo.domain.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> retrieveAll();

    Optional<Product> retrieveById(String id);

    Product save(Product product);

    void delete(String id);

    boolean exists(String id);

}
