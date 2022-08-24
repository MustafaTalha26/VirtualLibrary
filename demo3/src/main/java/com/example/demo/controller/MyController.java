package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.bean.User;
import com.example.demo.bean.UserRepo;
import com.example.demo.bean.User_Register;
import com.example.demo.bean.Blog;

@Controller
public class MyController {
	
	@Autowired
	private UserRepo repo;

    @GetMapping("/addUser")
    public String sendForm(User user) {
    	System.out.println("Bloodworm");
        return "addUser";
    }

    @PostMapping("/addUser")
    public String processForm(User user) {
    	List<Blog> blogg = repo.findByTitle(user.getName());
    	for(Blog oneblog : blogg) {
    		if(oneblog.getContent().equals(user.getOccupation())) {
    			System.out.println(oneblog.getId());
            	System.out.println(oneblog.getTitle());
            	System.out.println(oneblog.getContent());
    		}
    	}
    	return "showMessage";
    }
    
    @PostMapping("/createUser")
    public String createUser(User user) {
    	Blog blog = new Blog(user.getName(),user.getOccupation());
    	repo.save(blog);
    	return "showMessage";
    }
    
    @PostMapping("/createUserFr")
    public String createUserFr(User_Register register) {
    	return "";
    }
    
}
