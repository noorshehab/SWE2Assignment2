package com.store.customerservice;

import java.util.Collection;

public interface database<T,S> {
    void add(T obj) ;

    // Remove an order from the OrderDB based on the code
   void remove(S code) ;
    // Search for an order in the OrderDB based on the code
    T search(S code);

    // Get all orders in the OrderDB
    Collection<T> getAll();

}
