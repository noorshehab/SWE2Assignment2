package com.store.customerservice.Services;
import org.springframework.stereotype.Service;

interface Subject {
	public void Notify();
	public void registerObserver(Observer observer);
	public void removeObserver();
}