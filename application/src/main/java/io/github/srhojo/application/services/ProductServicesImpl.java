package io.github.srhojo.application.services;

import io.github.srhojo.domain.models.Product;
import io.github.srhojo.domain.repositories.ProductRepository;
import io.github.srhojo.domain.services.ProductServices;
import io.github.srhojo.utils.exceptions.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServicesImpl implements ProductServices {

    private final ProductRepository productRepository;

    public ProductServicesImpl(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> retrieveAll() {
        return productRepository.retrieveAll();
    }

    @Override
    public Product retrieveById(final String id) {

        return productRepository.retrieveById(id)
                .orElseThrow(()
                        -> new CustomException(HttpStatus.NOT_FOUND, "101", String.format("Product with id=%s not found ", id)));
    }

    @Override
    public Product create(final Product product) {
        product.setId(UUID.randomUUID().toString());
        return productRepository.save(product);
    }

    @Override
    public Product update(final String id, final Product product) {
        checkProduct(id);
        product.setId(id);
        return productRepository.save(product);
    }

    @Override
    public void delete(final String id) {
        checkProduct(id);
        productRepository.delete(id);
    }

    private void checkProduct(String id) {
        if (!productRepository.exists(id)) {
            throw new CustomException(HttpStatus.NOT_FOUND, "101", String.format("Product with id=%s not found ", id));
        }
    }
}
