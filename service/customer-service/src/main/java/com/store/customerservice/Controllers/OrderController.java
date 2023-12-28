package com.store.customerservice.Controllers;

import com.store.customerservice.Models.Order;
import com.store.customerservice.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class OrderController {

    @Autowired
    OrderService orderService;
    @PostMapping("/createSimpleOrder/{CustomerId}/{Address}")
    public String CreateOrder(@PathVariable("CustomerId") int cid,@PathVariable("Address") String add ){
        //check customer exists else return 404 + message

        return orderService.CreateOrder(cid,add);

    }
    @PostMapping("/createCompoundOrder/{CustomerId}/{Address}")
    public String CreateCompoundOrder(@PathVariable("CustomerId") int cid,@PathVariable("Address") String add ){
        //check customer exists else return 404 + message

        return orderService.CreateCompoundOrder(cid,add);

    }
    @PutMapping("/AddToOrder/{orderid}/{productid}/{Quantity}")
    public String AddToOrder(@PathVariable("orderid")int orderid, @PathVariable("productid")String productid, @PathVariable("Quantity")int q){
        return orderService.AddItem(orderid,productid,q);
    }
}
