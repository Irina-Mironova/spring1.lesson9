package ru.geekbrains.spring1.lesson9.entities;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Valid
@RequiredArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;


    @Column(name = "price")
    @NotNull(message = "Необходимо заполнить цену")
    @Min(value = 1, message = "Цена не может быть меньше 1")
    private float price;

    @ManyToOne
    @NotNull(message = "Необходимо заполнить категорию")
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "product",fetch = FetchType.EAGER)
    private List<ProductImage> images;

    public void addImage(ProductImage productImage){
        if (images == null){
            images = new ArrayList<>();
        }
        images.add(productImage);
    }
}
