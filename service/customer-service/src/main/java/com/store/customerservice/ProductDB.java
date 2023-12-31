package com.store.customerservice;

import com.store.customerservice.Models.LineItem;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ProductDB implements database<LineItem,String>{
    private Map<String, LineItem> lineItems;

    public ProductDB() {
        this.lineItems = new HashMap<>();
    }

    // Add a LineItem to the ProductDB
    @Override
    public void add(LineItem lineItem) {
        lineItems.put(lineItem.getProduct().getSerialNumber(), lineItem);
    }

    // Remove a LineItem from the ProductDB based on the serial number
    @Override
    public void remove(String serialNumber) {
        lineItems.remove(serialNumber);
    }

    // Search for a LineItem in the ProductDB based on the serial number
    @Override
    public LineItem search(String serialNumber) {
        return lineItems.get(serialNumber);
    }

    // Change the quantity of a LineItem in the ProductDB based on the serial number
    @Override

    //Get All
    public Collection<LineItem> getAll(){
        return lineItems.values();
    }
}

