package io.github.srhojo.data.mappers;

import io.github.srhojo.data.entities.ProductEntity;
import io.github.srhojo.domain.models.Product;
import io.github.srhojo.utils.mappers.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements Mapper<ProductEntity, Product> {

    @Override
    public ProductEntity mapToInner(final Product outer) {
        final ProductEntity inner = new ProductEntity();
        inner.setId(outer.getId());
        inner.setName(outer.getName());
        inner.setPrice(outer.getPrice());
        return inner;
    }

    @Override
    public Product mapToOuter(final ProductEntity inner) {
        return new Product(inner.getId(), inner.getName(), inner.getPrice());
    }
}
