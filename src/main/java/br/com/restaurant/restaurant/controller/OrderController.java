package br.com.restaurant.restaurant.controller;

import br.com.restaurant.restaurant.model.Order;
import br.com.restaurant.restaurant.service.OrderService;
import lombok.extern.flogger.Flogger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Order>> getAllOrders(){
        List<Order> orders = this.orderService.getAllOrder();

        return ResponseEntity.ok().body(orders);
    }
}
