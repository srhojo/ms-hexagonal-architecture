package io.github.srhojo.application.services;

import io.github.srhojo.domain.models.Product;
import io.github.srhojo.domain.repositories.ProductRepository;
import io.github.srhojo.utils.exceptions.CustomException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServicesImplTest {
    @InjectMocks
    private ProductServicesImpl productServices;

    @Mock
    private ProductRepository productRepository;


    @Test
    void retrieveAll() {
        // Given
        given(productRepository.retrieveAll()).willReturn(Collections.singletonList(new Product()));

        // When
        final List<Product> products = productServices.retrieveAll();

        // Then
        then(productRepository).should(only()).retrieveAll();
        assertNotNull(products);
        assertFalse(products.isEmpty());
        assertEquals(1, products.size());
    }

    @Test
    void retrieveById() {
        // Given
        String productId = "id";
        given(productRepository.retrieveById(productId)).willReturn(Optional.of(new Product()));

        // When
        Product response = productServices.retrieveById(productId);


        // Then
        then(productRepository).should(only()).retrieveById(productId);
        assertNotNull(response);
    }


    @Test()
    void retrieveById_notFound() {
        // Given
        String productId = "id";
        given(productRepository.retrieveById(productId)).willReturn(Optional.empty());

        // When
        final CustomException exception = Assertions.assertThrows(CustomException.class, ()
                -> productServices.retrieveById(productId));

        // Then
        then(productRepository).should(only()).retrieveById(productId);
        assertNotNull(exception);
        assertEquals("101", exception.getCode());
        assertEquals("Product with id=id not found ", exception.getDetails());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    void create() {
        // Given
        final Product newProduct = new Product();
        given(productRepository.save(newProduct)).willReturn(new Product());

        // When
        final Product response = productServices.create(newProduct);

        // Then
        then(productRepository).should(only()).save(newProduct);
        assertNotNull(response);
        assertNotNull(newProduct.getId());
    }

    @Test
    void update() {
        // Given
        final String id = "productId";
        final Product product = new Product();
        given(productRepository.exists(id)).willReturn(Boolean.TRUE);
        given(productRepository.save(product)).willReturn(new Product());

        // When
        final Product response = productServices.update(id, product);

        // Then
        then(productRepository).should(times(1)).exists(id);
        then(productRepository).should(times(1)).save(product);
        verifyNoMoreInteractions(productRepository);
        assertNotNull(response);
        assertEquals(id, product.getId());
    }


    @Test
    void update_productNotFound() {
        // Given
        final String id = "productId";
        final Product product = new Product();
        given(productRepository.exists(id)).willReturn(Boolean.FALSE);

        // When
        final CustomException exception = Assertions.assertThrows(CustomException.class, ()
                -> productServices.update(id, product));

        // Then
        then(productRepository).should(only()).exists(id);
        assertNotNull(exception);
        assertNull(product.getId());
        assertEquals("101", exception.getCode());
        assertEquals("Product with id=productId not found ", exception.getDetails());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    void delete() {
        // Given
        final String id = "productId";
        given(productRepository.exists(id)).willReturn(Boolean.TRUE);
        willDoNothing().given(productRepository).delete(id);


        // When
        productServices.delete(id);

        // Then
        then(productRepository).should(times(1)).exists(id);
        then(productRepository).should(times(1)).delete(id);
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    void delete_productNotFound() {
        // Given
        final String id = "productId";
        given(productRepository.exists(id)).willReturn(Boolean.FALSE);

        // When
        final CustomException exception = Assertions.assertThrows(CustomException.class, ()
                -> productServices.delete(id));

        // Then
        then(productRepository).should(only()).exists(id);
        assertNotNull(exception);
        assertEquals("101", exception.getCode());
        assertEquals("Product with id=productId not found ", exception.getDetails());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }
}