package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.bean.Register;
import com.example.demo.bean.User;
import com.example.demo.bean.UserRepo;

@Controller
public class MyController {
	
	@Autowired
	private UserRepo repo;

    @GetMapping("/RegisterPage")
    public String sendForm(Register register) {
    	System.out.println("Bloodworm");
        return "RegisterPage";
    }
    @PostMapping("/createUser")
    public String createUser(Register register) {
    	User user = new User(register.getFirst(),register.getSecond(),register.getEmail(),register.getPhone(),register.getPassword(),0);
    	repo.save(user);
    	System.out.println("Goldfish");
    	return "showMessage";
    }
    @GetMapping("/LoginPage")
    public String enterLoginPage(Register register) {
    	System.out.println("Bloodhorn");
    	return "LoginPage";
    }
    @PostMapping("/Panel")
    public String checkLogin(Register register) {
    	List<User> userList = repo.findByemail(register.getEmail());
    	for(User oneblog : userList) {
    		if(oneblog.getPassword().equals(register.getPassword())) {
    			System.out.println("Succesful login");
    			register.setFirst(oneblog.getFirstname());
    			return "Panel";
    		}
    	}
    	System.out.println("Wrong Password");
		return "LoginPage";
    }
    @GetMapping("/Panel")
    public String refreshPanel(Register register) {
    	if(register.getEmail() == null) {
    		return "LoginPage";
    	}
    	else {
    		return "Panel";
    	}
    }
}
