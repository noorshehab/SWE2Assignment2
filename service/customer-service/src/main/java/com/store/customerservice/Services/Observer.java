package com.store.customerservice.Services;

import com.store.customerservice.Models.*;

import java.util.Collection;
import java.util.Queue;

import org.springframework.stereotype.Service;

@Service
public interface Observer {
	public void update(String state, Collection<LineItem> items , int CustomerId);
	public Queue<NotificationTemplet> getTemplets();
}
