package com.store.customerservice;

import com.store.customerservice.Models.LineItem;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ProductDB {
    private Map<String, LineItem> lineItems;

    public ProductDB() {
        this.lineItems = new HashMap<>();
    }

    // Add a LineItem to the ProductDB
    public void addLineItem(LineItem lineItem) {
        lineItems.put(lineItem.getProduct().getSerialNumber(), lineItem);
    }

    // Remove a LineItem from the ProductDB based on the serial number
    public void removeLineItem(String serialNumber) {
        lineItems.remove(serialNumber);
    }

    // Search for a LineItem in the ProductDB based on the serial number
    public LineItem searchLineItem(String serialNumber) {
        return lineItems.get(serialNumber);
    }

    // Change the quantity of a LineItem in the ProductDB based on the serial number
    public void changeQuantity(String serialNumber, int newQuantity) {
        LineItem lineItem = lineItems.get(serialNumber);
        if (lineItem != null) {
            lineItem.setQuantity(newQuantity);
        }
    }

    public Collection<LineItem> getAllLineItems(){
        return lineItems.values();
    }
}

