package com.store.customerservice;

import com.store.customerservice.Models.Order;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class OrderDB {
    private Map<Integer, Order> orders;

    public OrderDB() {
        this.orders = new HashMap<>();
    }

    // Add an order to the OrderDB
    public void addOrder(Order order) {
        orders.put(order.getCode(), order);
    }

    // Remove an order from the OrderDB based on the code
    public void removeOrder(int code) {
        orders.remove(code);
    }

    // Search for an order in the OrderDB based on the code
    public Order searchOrder(int code) {
        return orders.get(code);
    }

    // Get all orders in the OrderDB
    public Collection<Order> getAllOrders() {
        return orders.values();
    }
}
