package com.store.customerservice.Controllers;

import com.store.customerservice.Models.NotificationTemplet;
import com.store.customerservice.Models.Customer;
import com.store.customerservice.Services.NotificationManager;
import com.store.customerservice.Services.Observer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class NotificationController {
	@Autowired
    Observer notificationManager;
	
	@GetMapping("/Notification/AllTemplets")
	public String show(){
		if (!notificationManager.getTemplets().isEmpty()) {
			StringBuilder finalString = new StringBuilder();
			for (NotificationTemplet temp : notificationManager.getTemplets()) {
				finalString.append(temp.getContent());
			}
			return finalString.toString();
		}
		return "No templates are Found";
    }
	
	@GetMapping("/Notification/MostNotifiedUser")
	public String MostNotifiedUser() {
		Cusomer cusomer = Observer.MostCusomerVisited();
		String massage = "The user: " + cusomer.getName() +  ", Email and Phone number:" + cusomer.getEmail() + " / " +  cusomer.getPhoneNumber() + " is the most Notified User.\n";
		return massage;
	}
	
	@GetMapping("/Notification/MostSendedTemplet")
	public String MostSendedTemplet() {
		String massage = Observer.MostTempletsSend();
		return massage;
	}
}
