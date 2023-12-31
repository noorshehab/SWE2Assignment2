package com.store.customerservice.Models;
import com.store.customerservice.Services.Observer;
import org.springframework.stereotype.Service;

public interface Subject {
	void Notify();
	 void registerObserver(Observer observer);
	 void removeObserver();
}