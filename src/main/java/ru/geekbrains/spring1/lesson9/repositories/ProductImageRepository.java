package ru.geekbrains.spring1.lesson9.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.spring1.lesson9.entities.ProductImage;

@Repository
public interface ProductImageRepository extends CrudRepository<ProductImage, Long> {
}
