package com.store.customerservice.Services;

import com.store.customerservice.Models.Order;
import com.store.customerservice.Models.Product;
import com.store.customerservice.Models.SimpleOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class SimpleOrderAssembly implements OrderAssembly{
    NotificationManager notificationManager=new NotificationManager();
    @Override
    public Order createOrder(int CustID,String Address) {
        SimpleOrder neworder=new SimpleOrder();
        neworder.setAddress(Address);
        neworder.setCustomerID(CustID);
        neworder.setCode();
        neworder.registerObserver(notificationManager);
        neworder.setStatus("Not Checked Out");
        return neworder;
    }

}
