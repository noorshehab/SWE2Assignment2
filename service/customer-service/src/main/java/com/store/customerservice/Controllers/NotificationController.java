package com.store.customerservice.Controllers;

import com.store.customerservice.Models.NotificationTemplet;
import com.store.customerservice.Services.NotificationManager;
import com.store.customerservice.Services.Observer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class NotificationController {
	@Autowired
    Observer notificationManager=new NotificationManager();
	
	@GetMapping("/Notification")
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
}
