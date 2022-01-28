package ru.geekbrains.spring1.lesson9.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.spring1.lesson9.services.ShoppingCartService;
import ru.geekbrains.spring1.lesson9.utils.ShoppingCart;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {
    private ShoppingCartService shoppingCartService;

    @Autowired
    public CartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public String cartPage(Model model, HttpSession httpSession) {
        ShoppingCart cart = shoppingCartService.getCurrentCart(httpSession);
        model.addAttribute("cart", cart);
        return "cart-page";
    }

}
