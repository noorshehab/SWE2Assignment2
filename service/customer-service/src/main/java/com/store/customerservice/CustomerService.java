package com.store.customerservice;
import com.store.customerservice.Models.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService implements CustomerDB {
    private final List<Customer> customers;
    private final List<Customer> loggedInCustomers;
    private static CustomerService Instance;
    public static CustomerService getInstance(){
        if(Instance==null){
            Instance=new CustomerService();
        }
        return Instance;
    }
    public CustomerService()
    {
        loggedInCustomers =new ArrayList<>();
        customers = new ArrayList<>();
    }
    @Override
    public String addCustomer(Customer customer)
    {
            if(isRegistered(customer.getEmail(),customer.getPassword()))
            {
                return "Email : " + customer.getEmail() +" is already existed";
            }

        customers.add(customer);
        return customer.toString();
    }

    @Override
    public void AddLoggedIn (Customer customer)
    {
        loggedInCustomers.add(customer);
    }

    @Override
    public boolean isLoggedIn (int id)
    {
        for (Customer c: customers) {
            if(id==c.getId())
                return true;
        }
        return false;
    }
    @Override
    public boolean isRegistered(String email, String password)
    {
        for (Customer c: customers) {
            if(email.equals(c.getEmail()) && password.equals(c.getPassword()))
            {
                return true;
            }
        }
        return false;
    }
    @Override
    public Customer getCustomer(int id)
    {
        for (Customer c: customers) {
            if(id==c.getId())
                return c;
        }
        return null;
    }

}

