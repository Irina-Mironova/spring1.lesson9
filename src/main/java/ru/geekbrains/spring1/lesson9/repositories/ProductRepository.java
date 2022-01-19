package ru.geekbrains.spring1.lesson9.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.spring1.lesson9.entities.Product;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
}
