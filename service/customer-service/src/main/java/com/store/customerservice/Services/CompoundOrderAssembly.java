package com.store.customerservice.Services;

import com.store.customerservice.Models.CompoundOrder;
import com.store.customerservice.Models.Order;
import com.store.customerservice.Models.SimpleOrder;

import java.util.ArrayList;

public class CompoundOrderAssembly implements OrderAssembly {
    private SimpleOrderAssembly simple=new SimpleOrderAssembly();
    @Override
    public Order createOrder(int CustID,String Address) {
        Order first=simple.createOrder(CustID,Address);
        Order order= new CompoundOrder();
        order.setCustomerID(CustID);
        order.setAddress(Address);
        order.setCode();
        order.setStatus("Not Checked Out");
        order.addOrder(first);
        return order;
    }
}
