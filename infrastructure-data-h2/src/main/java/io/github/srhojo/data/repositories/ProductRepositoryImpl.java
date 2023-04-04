package io.github.srhojo.data.repositories;

import io.github.srhojo.data.entities.ProductEntity;
import io.github.srhojo.data.jpa.ProductJpaRepository;
import io.github.srhojo.data.mappers.ProductMapper;
import io.github.srhojo.domain.models.Product;
import io.github.srhojo.domain.repositories.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;
    private final ProductMapper productMapper;

    public ProductRepositoryImpl(final ProductJpaRepository productJpaRepository, final ProductMapper productMapper) {
        this.productJpaRepository = productJpaRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<Product> retrieveAll() {
        final List<ProductEntity> products = (List<ProductEntity>) productJpaRepository.findAll();
        return productMapper.toOuter(products);
    }

    @Override
    public Optional<Product> retrieveById(final String id) {
        final ProductEntity product = productJpaRepository.findById(id).orElse(null);
        return productJpaRepository.findById(id).map(p -> productMapper.toOuter(product));

    }

    @Override
    public Product save(final Product product) {
        final ProductEntity entity = productMapper.toInner(product);
        return productMapper.toOuter(productJpaRepository.save(entity));
    }

    @Override
    public void delete(final String id) {
        if (productJpaRepository.existsById(id)) {
            productJpaRepository.deleteById(id);
        }
    }

    @Override
    public boolean exists(final String id) {
        return productJpaRepository.existsById(id);
    }
}
