package com.store.customerservice.Services;

import com.store.customerservice.CustomerDB;
import com.store.customerservice.Models.Customer;
import com.store.customerservice.Models.LineItem;
import com.store.customerservice.Models.Order;
import com.store.customerservice.Models.Product;
import com.store.customerservice.OrderDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class OrderService {
    @Autowired
    ProductService productService;
    @Autowired
    CustomerDB customerDB;

    private OrderAssembly simple=new SimpleOrderAssembly();
    private OrderAssembly compound=new CompoundOrderAssembly();
    private OrderDB orderdb=new OrderDB();

    //makeOrderforCustomer
    public String CreateOrder(int custid, String Address){
        //Customer customer=customerDB.getAll(custid);
        //if(customer!=null){
        Order neworder= simple.createOrder(custid,Address);
        orderdb.addOrder(neworder);
        return neworder.viewOrder();
       // }
        //return "Customer not found ";
    }
    public String AddItem(int oid,String pid,int quantity){
        Product found=productService.getProduct(pid);
        if(found==null){
            return "Product Not Found";
        }
        if(productService.getQuantity(pid)<quantity){
            return "Not Enough product In inventory";
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
    public String AddorderToCompound(int CID,int SID){
        Order compound = orderdb.searchOrder(CID);
        if(compound==null){
            return "Compound Order doesnt exist";
        }
        Order simple = orderdb.searchOrder(SID);
        if(simple==null){
            return "Order to add doesnt exist";
        }
        compound.addOrder(simple);
        return compound.viewOrder();
    }
    public String RemoveorderFromCompound(int CID,int SID){
        Order compound = orderdb.searchOrder(CID);
        if(compound==null){
            return "Compound Order doesnt exist";
        }
        Order simple = orderdb.searchOrder(SID);
        if(simple==null){
            return "Order to add doesnt exist";
        }
        compound.removeOrder(simple);
        return compound.viewOrder();
    }
    public String LookUpOrder(int id){
        Order order=orderdb.searchOrder(id);
        if(order!=null){
            return order.viewOrder();
        }
        return "Order not found";
    }
    public String viewAllOrders(){
        Collection<Order> orders=orderdb.getAllOrders();
        StringBuilder result = new StringBuilder();
        for (Order order : orders) {
            result.append(order.toString()).append("\n");
        }

        return result.toString();
    }

    public String CreateCompoundOrder(int custid, String Address){
        Order order=compound.createOrder(custid,Address);
        orderdb.addOrder(order);
        return order.viewOrder();
    }
    //CheckoutOrder
    //ShipOrder
    //CancelOrder

}
