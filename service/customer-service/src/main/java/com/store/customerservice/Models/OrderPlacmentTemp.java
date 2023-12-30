package com.store.customerservice.Models;

import java.util.ArrayList;
import java.util.Collection;

public class OrderPlacmentTemp extends NotificationTemplet{
	
	public OrderPlacmentTemp(String subject, String content, ArrayList<Channel> avilabaleChannels, String type, String languagge,
			Collection<LineItem> lineItems, String name) {
		
		super(subject, content, avilabaleChannels, type, languagge, lineItems, name);
		
	}

}
