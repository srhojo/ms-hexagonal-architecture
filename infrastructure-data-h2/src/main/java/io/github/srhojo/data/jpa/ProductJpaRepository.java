package io.github.srhojo.data.jpa;

import io.github.srhojo.data.entities.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductJpaRepository extends CrudRepository<ProductEntity, String> {
}
