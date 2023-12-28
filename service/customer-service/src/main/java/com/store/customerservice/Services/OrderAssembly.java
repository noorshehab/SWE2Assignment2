package com.store.customerservice.Services;

import com.store.customerservice.Models.Order;

import java.util.ArrayList;

public interface OrderAssembly {
    Order createOrder(int CustID,String Address);
}

