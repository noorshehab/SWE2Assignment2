package com.store.customerservice.Services;

import com.store.customerservice.Models.Customer;
import com.store.customerservice.Models.LineItem;
import com.store.customerservice.Models.Order;
import com.store.customerservice.Models.Product;

import com.store.customerservice.Models.NotificationTemplet;
import com.store.customerservice.Models.Channel;
import com.store.customerservice.Models.SMS;
import com.store.customerservice.Models.Email;
import com.store.customerservice.Models.OrderShippmentTemp;
import com.store.customerservice.Models.OrderPlacmentTemp;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Collection;

@Service
public class NotificationManager implements Observer{
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
    
    public Queue<NotificationTemplet> getTemplets() {
		return templets;
	}
	public void setTemplets(Queue<NotificationTemplet> templets) {
		this.templets = templets;
	}
	
	@Override
	public void update(String state, Collection<LineItem> items , Customer customer) {
		String subject;
		String content;
		
		Channel email = new Email(customer.getEmail());
		Channel sms = new SMS(customer.getPhoneNumber());
		avilabaleChannels.add(email);
		avilabaleChannels.add(sms);

		Channel channel = new Email(customer.getEmail());
		if (state == "Placement") {
			subject = "Notification about the order placement";

			content = "Hello Master " + customer.getName() + ", your booking for ";
			for (LineItem item : items) {
			    content += item.getProduct().getName() + " ";
			}
			content += "is confirmed. Thanks for using our store (:\n";

			temp = new OrderPlacmentTemp(subject, content, avilabaleChannels, "Order Placement", "English",
					items, customer.getName());
		}
		else if (state == "Shipment") {
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
			throw new RuntimeException("Erorr");
		}
		if (templets.size() == 8) {
			removeTemplets();
		}
		templets.add(temp);
	}

}
