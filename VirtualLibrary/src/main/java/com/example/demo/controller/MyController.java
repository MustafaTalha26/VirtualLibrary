package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.demo.bean.Register;
import com.example.demo.bean.User;
import com.example.demo.bean.UserRepo;

@Controller
@SessionAttributes({"register"})
public class MyController {
	
	@Autowired
	private UserRepo repo;

    @GetMapping("/RegisterPage")
    public String sendForm(Register register,SessionStatus status) {
    	status.setComplete();
        return "RegisterPage";
    }
    @PostMapping("/createUser")
    public String createUser(Register register) {
    	User user = new User(register.getFirst(),register.getSecond(),register.getEmail(),register.getPhone(),register.getPassword(),0);
    	repo.save(user);
    	System.out.println("Succesful register");
    	return "LoginPage";
    }
    @GetMapping("/LoginPage")
    public String enterLoginPage(Register register) {
    	return "LoginPage";
    }
    @PostMapping("/Panel")
    public String checkLogin(Register register) {
    	List<User> userList = repo.findByemail(register.getEmail());
    	for(User oneblog : userList) {
    		if(oneblog.getPassword().equals(register.getPassword())) {
    			register.setFirst(oneblog.getFirstname());
    			register.setSecond(oneblog.getLastname());
    			register.setPhone(oneblog.getPhonenumber());
    			register.setTerm(oneblog.getTerm());
    			System.out.println("Succesful login");
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
