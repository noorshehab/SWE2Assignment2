package com.store.customerservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LogRegController {
    private Authentication auth;

    private CustomerDB db;

    public LogRegController(Authentication auth) {
        this.auth = auth;
    }

    @Autowired
    public void setCustomerDB(CustomerDB db) {
        this.db = db;
    }
    @PostMapping("/customer/register")
    public String register(@RequestBody Customer c)
    {
        //return db.addCustomer(c);
        auth = new Register(db);
        return auth.execute(c);
    }

    public LogRegController(){}
    @PostMapping("/customer/login")
    public String log(@RequestBody Customer c)
    {
        auth = new Login(db);
        return auth.execute(c);
    }

    @GetMapping("/customer/getAll/{id}")
    public Customer getAll(@PathVariable("id") int id)
    {
        return db.getAll(id);
    }
}
