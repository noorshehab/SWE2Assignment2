package com.store.customerservice.Models;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public interface Order {

    public  String viewOrder();
    public Collection<Order> GetChildren();
    public void setStatus(String status);
    public String getStatus();
    public int GetCustomerID();
    public void setCustomerID(int id);
    public int getCode();
    public void setCode();
    public String getAddress();
    public void setAddress(String Address);
    public void AddItem(LineItem l);
    public boolean removeItem(String pid);
    public Collection<LineItem>getItems();
    public boolean changeQuantity(String pid,int newq);

    public  void addOrder(Order O);

    public  void removeOrder(Order order);

    public double getTotal();

    public boolean isCompound();
    
    
}
