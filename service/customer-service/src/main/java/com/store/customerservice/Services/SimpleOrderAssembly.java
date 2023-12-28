package com.store.customerservice.Services;

import com.store.customerservice.Models.Order;
import com.store.customerservice.Models.Product;
import com.store.customerservice.Models.SimpleOrder;

import java.util.ArrayList;

public class SimpleOrderAssembly implements OrderAssembly{
    @Override
    public Order createOrder(int CustID,String Address) {
        SimpleOrder neworder=new SimpleOrder();
        neworder.setAddress(Address);
        neworder.setCustomerID(CustID);
        neworder.setCode();
        neworder.setStatus("Not Checked Out");
        System.out.println("code "+neworder.getCode()+"cust id "+ neworder.GetCustomerID()+"address "+neworder.getAddress()+"status "+neworder.getStatus());
        return neworder;
    }

}
