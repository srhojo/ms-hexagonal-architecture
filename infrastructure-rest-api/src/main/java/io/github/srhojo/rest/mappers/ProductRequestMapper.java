package io.github.srhojo.rest.mappers;

import io.github.srhojo.domain.models.Product;
import io.github.srhojo.rest.apis.domain.ProductRequest;
import io.github.srhojo.utils.mappers.InnerMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductRequestMapper implements InnerMapper<Product, ProductRequest> {
    @Override
    public Product mapToInner(final ProductRequest outer) {
        final Product inner = new Product();
        inner.setName(outer.getName());
        inner.setPrice(outer.getPrice());
        return inner;
    }
}
