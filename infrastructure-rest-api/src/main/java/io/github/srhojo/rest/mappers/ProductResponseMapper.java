package io.github.srhojo.rest.mappers;

import io.github.srhojo.domain.models.Product;
import io.github.srhojo.rest.apis.domain.ProductResponse;
import io.github.srhojo.utils.mappers.OuterMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductResponseMapper implements OuterMapper<ProductResponse, Product> {
    @Override
    public ProductResponse mapToOuter(Product inner) {
        final ProductResponse outer = new ProductResponse();
        outer.setId(inner.getId());
        outer.setName(inner.getName());
        outer.setPrice(inner.getPrice());
        return outer;
    }
}
