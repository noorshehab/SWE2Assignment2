package com.store.customerservice;

import com.store.customerservice.Models.Order;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class OrderDB implements database<Order,Integer> {
    private static OrderDB Instance;

    private Map<Integer, Order> orders;

    public static OrderDB getInstance() {
        if(Instance==null){
            Instance=new OrderDB();
        }
        return Instance;
    }

    public OrderDB() {
        this.orders = new HashMap<>();
    }

    // Add an order to the OrderDB
   @Override
    public void add(Order order) {
        orders.put(order.getCode(), order);
    }

    // Remove an order from the OrderDB based on the code
    @Override
    public void remove(Integer code) {
        orders.remove(code);
    }

    // Search for an order in the OrderDB based on the code
    @Override
    public Order search(Integer code) {
        return orders.get(code);
    }

    // Get all orders in the OrderDB
    @Override
    public Collection<Order> getAll() {
        return orders.values();
    }
}
