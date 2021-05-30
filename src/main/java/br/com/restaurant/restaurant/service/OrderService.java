package br.com.restaurant.restaurant.service;

import br.com.restaurant.restaurant.model.Order;
import br.com.restaurant.restaurant.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrder(){
        return this.orderRepository.findAll();
    }
}
