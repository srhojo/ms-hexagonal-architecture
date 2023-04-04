package io.github.srhojo.rest.apis;


import io.github.srhojo.rest.apis.domain.ProductRequest;
import io.github.srhojo.rest.apis.domain.ProductResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping(value = "/apis/v1/products")
public interface ProductsApi {

    @GetMapping
    List<ProductResponse> getProducts();

    @GetMapping("/{id}")
    ProductResponse getById(@PathVariable(value = "id") final String id);

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    ProductResponse createProduct(@RequestBody @Valid final ProductRequest request);

    @PutMapping("/{id}")
    ProductResponse updateProduct(@PathVariable(value = "id") final String id, @RequestBody @Valid final ProductRequest request);

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void deleteProduct(@PathVariable(value = "id") final String id);

}
