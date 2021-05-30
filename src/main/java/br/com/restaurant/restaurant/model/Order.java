package br.com.restaurant.restaurant.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Order {
    private Long id;
    private Client client;
    private Table table;
    private LocalDateTime order;
    private List<Dish> dishes;
}
