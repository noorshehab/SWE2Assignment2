package com.store.customerservice.Models;

import java.util.ArrayList;
import java.util.Collection;

public class OrderShippmentTemp extends NotificationTemplet{
	private String address;
	
	public OrderShippmentTemp(String subject, String content, ArrayList<Channel> avilabaleChannels, String type, String languagge,
			Collection<LineItem> lineItems, String name, String address) {
		
		super(subject, content, avilabaleChannels, type, languagge, lineItems, name);
		
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
