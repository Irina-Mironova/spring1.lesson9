package ru.geekbrains.spring1.lesson9.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MainController {
    @RequestMapping("/")
    public String home() {
        return "index";
    }
}
