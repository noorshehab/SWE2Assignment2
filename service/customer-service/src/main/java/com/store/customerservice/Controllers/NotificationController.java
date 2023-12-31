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
    private Observer notificationManager; //= new NotificationManager();
	
	@GetMapping("/Notification")
	public String show(){
		if (!notificationManager.getTemplets().isEmpity()) {
	    	for (NotificationTemplet temp : notificationManager.getTemplets()) {
	            return temp.getContent();
	        }
		} else {
			return "No templets are Found";
		}
    }
}
