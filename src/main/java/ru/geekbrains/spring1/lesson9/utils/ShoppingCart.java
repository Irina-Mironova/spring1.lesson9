package ru.geekbrains.spring1.lesson9.utils;

import lombok.Data;
import ru.geekbrains.spring1.lesson9.entities.OrderItem;
import ru.geekbrains.spring1.lesson9.entities.Product;

import java.util.ArrayList;
import java.util.List;

@Data
public class ShoppingCart {
    private List<OrderItem> items;
    private Double totalCost;

    public ShoppingCart() {
        this.items = new ArrayList<>();
        this.totalCost = 0.0;
    }

    public void add(Product product) {
        OrderItem orderItem = findOrderFromProduct(product);
        if (orderItem == null){
            orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setItemPrice(product.getPrice());
            orderItem.setQuantity(0L);
            orderItem.setId(0L);
            orderItem.setTotalPrice(0L);
            items.add(orderItem);
        }
        orderItem.setQuantity(orderItem.getQuantity() + 1);
        recalculate();
    }

    private void recalculate() {
        totalCost = 0.0;
        for (OrderItem o: items) {
            o.setTotalPrice(o.getQuantity() * o.getProduct().getPrice());
            totalCost += o.getTotalPrice();
        }
    }

    private OrderItem findOrderFromProduct(Product product) {
        return  items.stream().filter(o->o.getProduct().getId().equals(product.getId())).findFirst().orElse(null);
    }

    public void remove(Product product) {
        OrderItem orderItem = findOrderFromProduct(product);
        if (orderItem == null) {
            return;
        }
        items.remove(orderItem);
        recalculate();
    }

}
