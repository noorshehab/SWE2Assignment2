package com.store.customerservice;

import com.store.customerservice.Models.Customer;

public  interface CustomerDB {
    public String addCustomer(Customer customer);
    public void AddLoggedIn (Customer customer);
    public boolean isLoggedIn (int id);
    public boolean isRegistered(String email, String password);
    public Customer getCustomer(int id);
}
