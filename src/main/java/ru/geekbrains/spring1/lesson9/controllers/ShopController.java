package ru.geekbrains.spring1.lesson9.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.spring1.lesson9.entities.Product;
import ru.geekbrains.spring1.lesson9.services.ProductService;
import ru.geekbrains.spring1.lesson9.services.ShoppingCartService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ShopController {
    private static final int INITIAL_PAGE = 0;
    private static final int PAGE_SIZE = 5;

    private ProductService productService;
    private ShoppingCartService shoppingCartService;

    @Autowired
    public ShopController(ProductService productService, ShoppingCartService shoppingCartService) {
        this.productService = productService;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/cart/add/{id}")
    public String addProductToCart(Model model, @PathVariable("id") Long id, HttpServletRequest httpServletRequest) {
        shoppingCartService.addToCart(httpServletRequest.getSession(), id);
        String referrer = httpServletRequest.getHeader("referer");
        return "redirect:" + referrer;
    }

    @GetMapping("/cart/remove/{id}")
    public String removeProductFromCart(Model model, @PathVariable("id") Long id, HttpServletRequest httpServletRequest) {
        shoppingCartService.removeFromCart(httpServletRequest.getSession(), id);
        String referrer = httpServletRequest.getHeader("referer");
        return "redirect:" + referrer;
    }
}
