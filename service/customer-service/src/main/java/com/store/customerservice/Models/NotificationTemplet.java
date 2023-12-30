package com.store.customerservice.Models;

import java.util.ArrayList;
import java.util.Collection;

public abstract class NotificationTemplet {
	private String subject;
	private String content;
	protected ArrayList<Channel> avilabaleChannels;
	private String type;
	private String languagge;
	private Collection<LineItem> lineItems;
	private String name;
	
	public NotificationTemplet(String subject, String content, ArrayList<Channel> avilabaleChannels, String type, String languagge,
			Collection<LineItem> lineItems, String name) {
		this.subject = subject;
		this.content = content;
		this.avilabaleChannels = avilabaleChannels;
		this.type = type;
		this.languagge = languagge;
		this.lineItems = lineItems;
		this.name = name;
	}

	public String getContent() {
		return content;
	}
}
