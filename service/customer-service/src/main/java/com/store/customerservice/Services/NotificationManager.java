package com.store.customerservice.Services;

import com.store.customerservice.CustomerService;
import com.store.customerservice.Models.Customer;
import com.store.customerservice.Models.LineItem;


import com.store.customerservice.Models.NotificationTemplet;
import com.store.customerservice.Models.Channel;
import com.store.customerservice.Models.SMS;
import com.store.customerservice.Models.Email;
import com.store.customerservice.Models.OrderShippmentTemp;
import com.store.customerservice.Models.OrderPlacmentTemp;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Collection;


public class NotificationManager implements Observer{
	@Autowired
	CustomerService customerService;
    private Queue<NotificationTemplet> templets;
    
    NotificationTemplet temp;
 
    ArrayList<Channel> avilabaleChannels;

    

    NotificationManager(){
    	templets = new LinkedList<>();
    	avilabaleChannels = new ArrayList<>(2);
    }
    
    public void removeTemplets() {
    	templets.clear();
	}
    
	public void setTemplets(Queue<NotificationTemplet> templets) {
		this.templets = templets;
	}
	
	@Override
    public Queue<NotificationTemplet> getTemplets() {
		return templets;
	}
	
	@Override
	public void update(String state, Collection<LineItem> items , int CustomerID) {
		String subject;
		String content;
		Customer customer= customerService.getCustomer(CustomerID);
		Channel email = new Email(customer.getEmail());
		Channel sms = new SMS(customer.getPhoneNumber());
		avilabaleChannels.add(email);
		avilabaleChannels.add(sms);

		if (state.equals("Placement")) {
			subject = "Notification about the order placement";

			content = "Hello " + customer.getName() + ", your booking for ";
			for (LineItem item : items) {
			    content += item.getProduct().getName() + " ";
			}
			content += "is confirmed. Thanks for using our store (:\n";

			temp = new OrderPlacmentTemp(subject, content, avilabaleChannels, "Order Placement", "English",
					items, customer.getName());
		}
		else if (state.equals("Shipment")) {
			subject = "Notification about the order Shipment";

			content = "Hello Master " + customer.getName() + ", your order";
			for (LineItem item : items) {
			    content += " " + item.getProduct().getName() + " ";
			}
			content += " is shipping now to " + customer.getAddress() + ". Thanks for using our store (:\n";

			temp = new OrderShippmentTemp(subject, content, avilabaleChannels, "Order Shipment", "English",
					items, customer.getName(), customer.getAddress());
		}
		else {
			throw new RuntimeException("Error");
		}
		if (templets.size() == 8) {
			removeTemplets();
		}
		templets.add(temp);
	}

}
