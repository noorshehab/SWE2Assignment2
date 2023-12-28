package com.store.customerservice.Controllers;

import com.store.customerservice.Models.Order;
import com.store.customerservice.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
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
    @PostMapping("/ChangeQuantity/{orderid}/{Product}/{Quantity}")
    public String ChangeQuantity(@PathVariable("orderid")int orderid,@PathVariable("Product")String pid,@PathVariable("Quantity")int q){
        return orderService.ChangeQuantity(orderid,pid,q);
    }
    @PutMapping("/RemoveItem/{orderid}/{Product}")
    public String RemoveItem(@PathVariable("orderid")int orderid,@PathVariable("Product")String pid){
        return orderService.RemoveItem(orderid,pid);
    }
    @PutMapping("/RemoveOrderFrom/{CID}/{SID}")
    public String RemoveOrder(@PathVariable("CID")int CID,@PathVariable("SID")int SID){
        return orderService.RemoveorderFromCompound(CID, SID);
    }
    @PutMapping("/AddOrdertoOrder/{CID}/{SID}")
    public String AddOrderToOrder(@PathVariable("CID")int CID,@PathVariable("SID")int SID){
        return orderService.AddorderToCompound(CID, SID);
    }
    @GetMapping("/ViewOrder/{OID}")
    public String ViewOrder(@PathVariable("OID")int OID){
        return orderService.LookUpOrder(OID);
    }
    @GetMapping("/ViewAllOrders")
    public Collection<Order> ViewAll(){
        return orderService.viewAllOrders();
    }
}
