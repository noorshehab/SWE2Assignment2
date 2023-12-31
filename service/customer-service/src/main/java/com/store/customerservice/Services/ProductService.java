package com.store.customerservice.Services;

import com.store.customerservice.Models.LineItem;
import com.store.customerservice.Models.Product;
import com.store.customerservice.ProductDB;
import com.store.customerservice.database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
public class ProductService {
    database<LineItem,String> productDB;
    private static ProductService Instance;
    public static ProductService getInstance(){
        if(Instance==null){
            Instance=new ProductService();
        }
        return Instance;
    }


    public ProductService() {
        productDB=new ProductDB();
    }

    // Create a new product with a randomly generated serial number
    public LineItem createProduct(Product P) {
        String serialNumber = generateSerialNumber();
        P.setSerialNumber(serialNumber);
        LineItem lineItem = new LineItem(P,0); // Assuming initial quantity is 0
        productDB.add(lineItem);
        return lineItem;
    }

    public Product getProduct(String pid){
        LineItem Found=productDB.search(pid);
        if(Found==null){return null;}
        return Found.getProduct();
    }
    public int getQuantity(String pid){
        LineItem Found=productDB.search(pid);
        if(Found==null){return -1;}
        return Found.getQuantity();
    }

    // Get all LineItems in the ProductDB
    public Collection<LineItem> getAllLineItems() {
        return productDB.getAll();
    }

    public LineItem ChangeQuantity(String SerialNumber,int newq){
        LineItem tobUpdated =productDB.search(SerialNumber);
        tobUpdated.setQuantity(newq);
        return tobUpdated;
    }

    // Generate a random serial number
    private String generateSerialNumber() {
        return UUID.randomUUID().toString();
    }
}
