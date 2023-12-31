package com.store.customerservice.Models;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
@Service
public abstract class NotificationTemplet {
	private String subject;
	private String content;
	protected ArrayList<Channel> avilabaleChannels;
	private String type;
	private String languagge;
	private Collection<LineItem> lineItems;
	private String name;
	private int CustomerID;
	
	public NotificationTemplet(String subject, String content, ArrayList<Channel> avilabaleChannels, String type, String languagge,
			Collection<LineItem> lineItems, String name, int CustomerID) {
		this.subject = subject;
		this.content = content;
		this.avilabaleChannels = avilabaleChannels;
		this.type = type;
		this.languagge = languagge;
		this.lineItems = lineItems;
		this.name = name;
		this.CustomerID = CustomerID;
	}

	public String getContent() {
		return content;
	}
	public int getCustomerID() {
		return CustomerID;
	}
}
