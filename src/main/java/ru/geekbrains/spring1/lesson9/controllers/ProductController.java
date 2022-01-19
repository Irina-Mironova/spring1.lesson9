package ru.geekbrains.spring1.lesson9.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.spring1.lesson9.dtos.ProductDto;
import ru.geekbrains.spring1.lesson9.entities.Product;
import ru.geekbrains.spring1.lesson9.services.ProductImageService;
import ru.geekbrains.spring1.lesson9.services.ProductService;

@Controller
public class ProductController {
    private ProductService productService;
    private ProductImageService productImageService;

    @Autowired
    public ProductController(ProductService productService, ProductImageService productImageService) {
        this.productService = productService;
        this.productImageService = productImageService;
    }


    @GetMapping("/products")
    public String findAll(Model model, @RequestParam(defaultValue = "1") int indexPage) {
        if (indexPage < 1) {
            indexPage = 1;
        }
        Page<ProductDto> productPage = productService.findAll(indexPage - 1, 5).map(ProductDto::new);
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("totalProducts", productPage.getTotalElements());
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("currentPage", indexPage);
        return "productsAll";
    }

    @GetMapping("/productAdd")
    public String productAdd(Model model) {
        ProductDto productDto = new ProductDto();
        model.addAttribute("product", productDto);
        return "productAdd";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("product") @Validated ProductDto productDto,
                       @RequestParam("imagefile") MultipartFile file) {
        Product product = productService.save(productDto);
        productImageService.save(file, product);
        return "redirect:/products";
    }
}
