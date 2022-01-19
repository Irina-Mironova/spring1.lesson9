package ru.geekbrains.spring1.lesson9.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "products_images")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "path")
    private String path;

}
