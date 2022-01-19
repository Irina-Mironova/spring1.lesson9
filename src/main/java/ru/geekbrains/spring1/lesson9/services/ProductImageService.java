package ru.geekbrains.spring1.lesson9.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.spring1.lesson9.entities.Product;
import ru.geekbrains.spring1.lesson9.entities.ProductImage;
import ru.geekbrains.spring1.lesson9.repositories.ProductImageRepository;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.file.Paths;

@Service
public class ProductImageService {
    private ProductImageRepository productImageRepository;

    @Autowired
    public ProductImageService(ProductImageRepository productImageRepository) {
        this.productImageRepository = productImageRepository;
    }

    public void save(MultipartFile file, Product product) {
        if (!file.isEmpty()) {
            ProductImage productImage = new ProductImage();
            productImage.setProduct(product);
            productImage.setPath(file.getOriginalFilename());

            try {
                byte[] bytes = file.getBytes();
                URL resource = ProductImageService.class.getResource("/static");
                String path = Paths.get(resource.toURI()).toFile().getAbsolutePath() + "\\images\\" + file.getOriginalFilename();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(path)));
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            productImageRepository.save(productImage);
        }
    }
}

