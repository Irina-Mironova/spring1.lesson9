package ru.geekbrains.spring1.lesson9.dtos;

import lombok.Data;
import ru.geekbrains.spring1.lesson9.entities.Product;
import ru.geekbrains.spring1.lesson9.entities.ProductImage;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ProductDto {
    private Long id;

    @NotNull(message = "Необходимо заполнить название")
    private String title;

    @NotNull(message = "Необходимо заполнить цену")
    @Min(value = 1, message = "Цена не может быть меньше 1")
    private float price;

    @NotNull(message = "Необходимо заполнить категорию")
    private String category;

    private List<ProductImage> images;

    public ProductDto() {
    }

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.category = product.getCategory().getTitle();

        // this.setImages(product.getImages());
        this.images = product.getImages();

    }


}
