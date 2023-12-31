package com.store.customerservice.Services;

import com.store.customerservice.Models.Customer;
import com.store.customerservice.Models.LineItem;
import com.store.customerservice.Models.Order;
import com.store.customerservice.Models.Product;
import java.util.Collection;
import org.springframework.stereotype.Service;

@Service
interface Observer {
	public void update(String state, Collection<LineItem> items , Customer customer);
	public Queue<NotificationTemplet> getTemplets();
}
