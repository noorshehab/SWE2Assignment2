package com.store.customerservice.Models;
package com.store.customerservice.Services.Observer;

import lombok.Getter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import Test.Observer;

public class SimpleOrder implements Order , Subject{
    HashMap<String,LineItem>Items=new HashMap<>();

    int Customerid;

    int OrderID;
    
    Observer observer;
    
    @Getter
    String Address;


    @Getter
    String Status;


    public SimpleOrder() {

    }

    public void setStatus(String status) {
        Status = status;
        Notify();
    }
    @Override
    public int GetCustomerID(){return Customerid;}
    @Override
    public void setCustomerID(int customerid) {
        Customerid = customerid;
    }
    @Override
    public void setAddress(String address) {
        Address = address;
    }
 @Override
    public void setCode(){
        Random random= new Random();
        OrderID= random.nextInt();
    }
    @Override
    public String viewOrder() {

       return toString();
    }

    @Override
    public Collection<Order> GetChildren() {
        return null;
    }

    @Override
    public int getCode() {
        return OrderID;
    }

    @Override
    public void AddItem(LineItem l) {
        Items.put(l.getProduct().getSerialNumber(),l);
    }
//bool
    @Override
    public boolean removeItem(String pid) {
        LineItem tob=Items.get(pid);
        if(tob!=null){
            Items.remove(pid);
            return true;
        }
        return false;
    }

    @Override
    public Collection<LineItem> getItems() {
        return Items.values();
    }

    @Override
    //bool
    public boolean changeQuantity(String pid, int newq) {
        LineItem toChange=Items.get(pid);
        if(toChange!=null) {
            toChange.setQuantity(newq);
            return true;
        }
        return false;
    }


    @Override
    public double getTotal() {
        double total=0;
        for(Map.Entry<String,LineItem> entry:Items.entrySet()){
            total+=entry.getValue().getProduct().getPrice()*entry.getValue().getQuantity();
        }
        return total;
    }
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("OrderID: ").append(OrderID).append("\n");
        stringBuilder.append("CustomerID: ").append(Customerid).append("\n");
        stringBuilder.append("Address: ").append(Address).append("\n");
        stringBuilder.append("Status: ").append(Status).append("\n");

        // Display information about LineItems
        stringBuilder.append("LineItems:\n");
        for (Map.Entry<String, LineItem> entry : Items.entrySet()) {
            String serialNumber = entry.getKey();
            LineItem lineItem = entry.getValue();
            stringBuilder.append("\tSerialNumber: ").append(serialNumber)
                    .append(", Product: ").append(lineItem.getProduct().toString())
                    .append(", Quantity: ").append(lineItem.getQuantity()).append("\n");
        }

        return stringBuilder.toString();
    }

    @Override
    public boolean isCompound() {
        return false;
    }
    @Override
    public void addOrder(Order O) {

    }

    @Override
    public void removeOrder(Order order) {

    }
    
    @Override
	public void Notify() {
		observer.update(state, Items.values(), customer);
	}
	@Override
	public void removeObserver() {
		this.observer = null;
	}
	@Override
	public void registerObserver(Observer observer) {
		this.observer = observer;
		
	}
  

}
