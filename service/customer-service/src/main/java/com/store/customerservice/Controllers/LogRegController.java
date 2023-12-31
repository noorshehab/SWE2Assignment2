package com.store.customerservice.Controllers;

import com.store.customerservice.CustomerService;
import com.store.customerservice.Models.Authentication;
import com.store.customerservice.CustomerDB;
import com.store.customerservice.Models.Customer;
import com.store.customerservice.Services.Register;
import com.store.customerservice.Services.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class LogRegController {
    private Authentication auth;

    private CustomerDB db;

    public LogRegController(Authentication auth, CustomerDB db) {
        this.auth = auth;
        this.db = db;
    }

    @Autowired
    public void setCustomerDB(CustomerDB db) {
        this.db = CustomerService.getInstance();
    }
    @PostMapping("/customer/register")
    public String register(@RequestBody Customer c)
    {
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
    public Customer getCustomer(@PathVariable("id") int id)
    {
        return db.getCustomer(id);
    }
}
