package com.store.customerservice;
import com.store.customerservice.Models.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CustomerDB {
    private final List<Customer> customers;
    public CustomerDB()
    {
        customers = new ArrayList<>();
    }
    public String addCustomer(Customer customer)
    {
        for (Customer c: customers) {
            if(Objects.equals(customer.getEmail(), c.getEmail()))
            {
                return "Email : " + customer.getEmail() +" is already existed";
            }
        }
        customers.add(customer);
        return "Registered successfully";
    }

    public boolean isRegistered(String email, String password)
    {
        for (Customer c: customers) {
            if(email.equals(c.getEmail())&&password.equals(c.getPassword()))
            {
                return true;
            }
        }
        return false;
    }
    public Customer getAll(int id)
    {
        for (Customer c: customers) {
            if(id==c.getId())
                return c;
        }
        return null;
    }
}
