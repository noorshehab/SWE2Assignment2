package com.store.customerservice;

import org.springframework.stereotype.Service;

@Service
public class Login extends Authentication{
    private CustomerDB db;

    public Login(CustomerDB db) {
        this.db = db;
    }
    public Login(){}
    public Login(String email, String password)
    {
        super(email, password);
    }
    @Override
    public String execute(Customer customer) {
        String email = customer.getEmail();
        String password = customer.getPassword();
        if(db.isRegistered(email,password))
        {
            return "Logged in successfully";
        }
        return "Wrong email or password try again ";
    }
}
