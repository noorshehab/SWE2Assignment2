package com.store.customerservice.Models;

import lombok.Getter;

import java.util.*;

public class CompoundOrder implements Order{
    List<Order> Orders=new ArrayList<>();
    int CustomerId;
    String Status;

    int code;


    @Getter
    String Address;
    public CompoundOrder() {

    }
    @Override
    public int getCode() {
        return code;
    }
@Override
    public void setCode() {
       Random random =new Random();
       code=random.nextInt();
    }
    @Override
    public void setAddress(String address) {
        Address = address;
    }


    @Override
    public String viewOrder() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CompoundOrder Attributes:\n");
        stringBuilder.append("Code: ").append(code).append("\n");
        stringBuilder.append("CustomerID: ").append(CustomerId).append("\n");
        stringBuilder.append("Address: ").append(Address).append("\n");
        stringBuilder.append("Status: ").append(Status).append("\n");

        stringBuilder.append("Simple Orders:\n");
        for (Order order : Orders) {
            stringBuilder.append(order.toString());
        }

        return stringBuilder.toString();
    }

    @Override
    public Collection<Order> GetChildren() {
        return Orders;
    }

    @Override
    public void setStatus(String status) {
        Status=status;
    }

    @Override
    public String getStatus() {
        return Status;
    }

    @Override
    public int GetCustomerID() {
        return CustomerId;
    }

    @Override
    public void setCustomerID(int id) {
        CustomerId=id;
    }

    @Override
    public void addOrder(Order O) {
        Orders.add(O);
    }

    @Override
    public void removeOrder(Order order) {
        Orders.remove(order);
    }

    @Override
    public double getTotal() {
        double total=0;
       for(Order O:Orders){
           total+=O.getTotal();
       }
       return total;
    }

    @Override
    public boolean isCompound() {
        return true;
    }

    @Override
    public void AddItem(LineItem l) {

    }

    @Override
    public boolean removeItem(String pid) {
        return false;
    }

    @Override
    public Collection<LineItem> getItems() {
        return null;
    }

    @Override
    public boolean changeQuantity(String pid, int newq) {
        return false;
    }

}
