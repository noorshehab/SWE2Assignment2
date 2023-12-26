package com.store.customerservice;

import org.springframework.stereotype.Service;
@Service
public class Register extends Authentication {
    private CustomerDB db;
    private String phoneNumber;
    public Register(String email, String password, String phoneNumber, CustomerDB dp) {
        super(email, password);
        this.db = dp;
        this.phoneNumber = phoneNumber;
    }

    public Register(CustomerDB db) {
        this.db = db;
    }


    Register(){}
    @Override
    public String execute(Customer customer) {
       if(this.db==null)
           return "object db is null";

       else
            return db.addCustomer(customer);
    }

}
