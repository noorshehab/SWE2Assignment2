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
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Collection;


import java.util.HashMap;
import java.util.Map;
@Service
public class NotificationManager implements Observer{
	@Autowired
	CustomerService customerService;
    private Queue<NotificationTemplet> templets;
    
    NotificationTemplet temp;
 
    ArrayList<Channel> avilabaleChannels;

    //CusomerId, # of time
    Map<Integer, Integer> Visitors;
    //CustomerID, Templtes
    Map<Integer, ArrayList<NotificationTemplet>> TempletVistors;
    
    NotificationManager(){
    	templets = new LinkedList<>();
    	avilabaleChannels = new ArrayList<>(2);
    	TempletVistors = new HashMap<>();
    	Visitors = new HashMap<>();
    }
    
    //return CustomerId
    private int findMostUserIDVisited() {

        int maxValue = Integer.MIN_VALUE;
        int Target=-1;
        for (Map.Entry<Integer, Integer> entry : Visitors.entrySet()) {
            if (entry.getValue() > maxValue) {
            	Target = entry.getKey();
            	maxValue = entry.getValue();
            }
        }

        return Target;
    }
    
    public Customer MostCusomerVisited() {
    	int CustomerID = findMostUserIDVisited();
    	Customer customer= customerService.getCustomer(CustomerID);
    	return customer;
    }
    
    public String MostTempletsSend() {
    	int CustomerID = findMostUserIDVisited();
    	String Massage = "";
    	for (NotificationTemplet tp : TempletVistors[CustomerID]) {
    		Massage += tp.getContent();
    	}
    	return Massage;
    }
    
    public void removeTemplets() {
    	templets.clear();
	}
    
	public void setTemplets(Queue<NotificationTemplet> templets) {
		this.templets = templets;
	}
	
	public void CancelTemplet(int CustomerID) {
		ArrayList<NotificationTemplet> removeList = new ArrayList<>();
		for (NotificationTemplet temp : templets) {
			if (temp.getCustomerID == CustomerID) {
				removeList.add(temp);
			}
		}
		for (NotificationTemplet temp : templets) {
			templets.remove(temp);
		}
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

		if (Visitors.containsKey(CustomerID)) {
			int currentValue = Visitors.get(CustomerID);
            int newValue = currentValue + 1;
            Visitors.put(CustomerID, newValue);

            if (TempletVistors.get(CustomerID) < 2) {
            	TempletVistors.get(CustomerID).add(temp);
            }
		} else {
            Visitors.put(CustomerID, 1);
            TempletVistors.put(CustomerID, new ArrayList<>(2));
			TempletVistors.get(CustomerID).add(temp);
		}



	}

}
