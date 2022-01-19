package ru.geekbrains.spring1.lesson9.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring1.lesson9.dtos.ProductDto;
import ru.geekbrains.spring1.lesson9.entities.Category;
import ru.geekbrains.spring1.lesson9.entities.Product;
import ru.geekbrains.spring1.lesson9.exceptions.ResourceNotFoundException;
import ru.geekbrains.spring1.lesson9.repositories.ProductRepository;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private CategoryService categoryService;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }


    public Page<Product> findAll(int indexPage, int pageSize) {
        return productRepository.findAll(PageRequest.of(indexPage, pageSize));
    }

    public Product save(ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category category = categoryService.findByTitle(productDto.getCategory()).orElseThrow(() ->
                new ResourceNotFoundException("Категория с названием = " + productDto.getCategory() + " не найдена"));
        product.setCategory(category);
        productRepository.save(product);
        return product;
    }
}
