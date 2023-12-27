package com.store.customerservice.Services;

import com.store.customerservice.Models.LineItem;
import com.store.customerservice.Models.Product;
import com.store.customerservice.ProductDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
public class ProductService {
     ProductDB productDB;

    public ProductService(ProductDB productDB) {
        this.productDB = new ProductDB();
    }

    // Create a new product with a randomly generated serial number
    public LineItem createProduct(Product P) {
        String serialNumber = generateSerialNumber();
        P.setSerialNumber(serialNumber);
        LineItem lineItem = new LineItem(P,0); // Assuming initial quantity is 0
        productDB.addLineItem(lineItem);
        return lineItem;
    }

    // Get all LineItems in the ProductDB
    public Collection<LineItem> getAllLineItems() {
        return productDB.getAllLineItems();
    }

    public LineItem ChangeQuantity(String SerialNumber,int newq){
        LineItem tobUpdated =productDB.searchLineItem(SerialNumber);
        tobUpdated.setQuantity(newq);
        return tobUpdated;
    }

    // Generate a random serial number (for illustration purposes)
    private String generateSerialNumber() {
        return UUID.randomUUID().toString();
    }
}
