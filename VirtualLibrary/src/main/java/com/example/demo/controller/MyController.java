package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.demo.bean.Author;
import com.example.demo.bean.AuthorRepo;
import com.example.demo.bean.Book;
import com.example.demo.bean.BookRepo;
import com.example.demo.bean.BookandType;
import com.example.demo.bean.Register;
import com.example.demo.bean.User;
import com.example.demo.bean.UserRepo;

@Controller
@SessionAttributes({"register"})
public class MyController {
	
	@Autowired
	private UserRepo repo;
	
	@Autowired
	private BookRepo bookrepo;
	
	@Autowired
	private AuthorRepo autrepo;

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
    @GetMapping("/BookPage")
    public String getBookPage(Register register,Model model) {
    	if(register.getEmail() == null) {
    		return "LoginPage";
    	}
    	List<BookandType> full = new ArrayList<>();
    	List<Book> books = bookrepo.findAll();
    	for(Book book : books) {
    		String type = "";
    		if(book.getScifi() == 1) {
    			type += "Sci-Fi, ";
    		}
    		if(book.getDetective() == 1) {
    			type += "Detective, ";
    		}
    		if(!type.equals("")) {
        		type = type.substring(0, type.length()-2);
    		}
    		full.add(new BookandType(book,type));
    	}
    	model.addAttribute("books",full);
    	return "BookPage";
    }
    
    @GetMapping("/BookPageType")
    public String getTypedBookPage(Register register,Model model) {
    	if(register.getEmail() == null) {
    		return "LoginPage";
    	}
    	List<BookandType> full = new ArrayList<>();
    	List<Book> books = bookrepo.findAll();
    	for(Book book : books) {
    		String type = "";
    		if(book.getScifi() == 1) {
    			type += "Sci-Fi, ";
    		}
    		if(book.getDetective() == 1) {
    			type += "Detective, ";
    		}
    		if(!type.equals("")) {
        		type = type.substring(0, type.length()-2);
    		}
    		if(book.getScifi() == 1) {
    			full.add(new BookandType(book,type));
    		}
    	}
    	model.addAttribute("books",full);
    	return "BookPage";
    }
    
    @GetMapping("/AuthorPage")
    public String getAutPage(Register register,Model model) {
    	if(register.getEmail() == null) {
    		return "LoginPage";
    	}
    	List<Author> auths = autrepo.findAll();
    	model.addAttribute("auths",auths);
    	return "AuthorPage";
    }
}
