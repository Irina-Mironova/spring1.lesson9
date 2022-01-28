package ru.geekbrains.spring1.lesson9.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring1.lesson9.entities.Product;
import ru.geekbrains.spring1.lesson9.utils.ShoppingCart;

import javax.servlet.http.HttpSession;

@Service
public class ShoppingCartService {
    private ProductService productService;

    @Autowired
    public ShoppingCartService(ProductService productService) {
        this.productService = productService;
    }

    public ShoppingCart getCurrentCart(HttpSession httpSession) {
        ShoppingCart cart = (ShoppingCart) httpSession.getAttribute("cart");
        if (cart == null) {
            cart = new ShoppingCart();
            httpSession.setAttribute("cart", cart);
        }
        return cart;
    }

    public void addToCart(HttpSession httpSession, Long id) {
        Product product = productService.findById(id);
        addToCart(httpSession, product);
    }

    public void addToCart(HttpSession httpSession, Product product) {
        ShoppingCart cart = getCurrentCart(httpSession);
        cart.add(product);
    }

    public void removeFromCart(HttpSession httpSession, Long id) {
        Product product = productService.findById(id);
        removeFromCart(httpSession, product);
    }

    public void removeFromCart(HttpSession httpSession, Product product) {
        ShoppingCart cart = getCurrentCart(httpSession);
        cart.remove(product);
    }
}
