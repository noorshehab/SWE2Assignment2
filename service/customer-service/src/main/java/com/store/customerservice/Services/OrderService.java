package com.store.customerservice.Services;

import com.store.customerservice.Models.LineItem;
import com.store.customerservice.Models.Order;
import com.store.customerservice.Models.Product;
import com.store.customerservice.OrderDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    ProductService productService;

    private OrderAssembly simple=new SimpleOrderAssembly();
    private OrderAssembly compound=new CompoundOrderAssembly();
    private OrderDB orderdb=new OrderDB();

    //makeOrderforCustomer
    public String CreateOrder(int custid, String Address){
        Order neworder= simple.createOrder(custid,Address);
        orderdb.addOrder(neworder);
        return neworder.viewOrder();
    }
    public String AddItem(int oid,String pid,int quantity){
        Product found=productService.getProduct(pid);
        if(found==null){
            return "Product Not Found";
        }
        LineItem toadd=new LineItem(found,quantity);
        Order tob=orderdb.searchOrder(oid);
        if(tob==null){
            return "Order Not Found";
        }
        tob.AddItem(toadd);
        return tob.viewOrder();
    }
    public String RemoveItem(int oid,String pid){
        Order tob=orderdb.searchOrder(oid);
        if(tob==null){
            return "Could not find Order";
        }
        if(tob.removeItem(pid))
            return tob.viewOrder();
        return "Product Not In Order";
    }
    public String ChangeQuantity(int oid,String pid,int quantity){
        //check that quantity is available
        Order tob=orderdb.searchOrder(oid);
        if(tob==null){
            return "Could not find Order";
        }
        if(tob.changeQuantity(pid,quantity))
            return tob.viewOrder();
        return "Product Not In order";
    }
    //public Order AddorderToCompound();
    //public String ViewOrder();
    //makeCompoundOrder
    public String CreateCompoundOrder(int custid, String Address){
        Order order=compound.createOrder(custid,Address);
        orderdb.addOrder(order);
        return order.viewOrder();
    }
    //CheckoutOrder
    //ShipOrder
    //CancelOrder
    //ViewAllOrders
}
