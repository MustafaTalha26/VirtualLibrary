package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.demo.bean.Admin;
import com.example.demo.bean.AdminRepo;
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
	
	@Autowired
	private AdminRepo adminrepo;

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
    	List<Admin> admins = adminrepo.findAll();
    	for(User oneblog : userList) {
    		if(oneblog.getPassword().equals(register.getPassword())) {
    			for(Admin admin : admins) {
    				if(oneblog.getLid() == admin.getUserid()) {
    					register.setFirst(oneblog.getFirstname());
    	    			register.setSecond(oneblog.getLastname());
    	    			register.setPhone(oneblog.getPhonenumber());
    	    			register.setTerm(oneblog.getTerm());
    	    			register.setAdmin(1);
    	    			System.out.println("Succesful login");
    	    			return "AdminPanel";
    				}
    			}
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
    		full.add(new BookandType(book));
    	}
    	model.addAttribute("books",full);
    	return "BookPage";
    }
    
    @PostMapping("/BookPageType")
    public String getTypedBookPage(Register register,Model model,@RequestParam(value="type", required=true) String type) {
    	if(register.getEmail() == null) {
    		return "LoginPage";
    	}
    	List<BookandType> full = new ArrayList<>();
    	List<Book> books = bookrepo.findAll();
    	for(Book book : books) {
    		if(book.getScifi() == 1 && type.equals("scifi")) {
    			full.add(new BookandType(book));
    		}
    		if(book.getDetective() == 1 && type.equals("detective")) {
    			full.add(new BookandType(book));
    		}
    		if(book.getComic() == 1 && type.equals("comic")) {
    			full.add(new BookandType(book));
    		}
    		if(book.getCook() == 1 && type.equals("cook")) {
    			full.add(new BookandType(book));
    		}
    		if(book.getEducation() == 1 && type.equals("education")) {
    			full.add(new BookandType(book));
    		}
    		if(book.getHistory() == 1 && type.equals("history")) {
    			full.add(new BookandType(book));
    		}
    		if(book.getLiterature() == 1 && type.equals("literature")) {
    			full.add(new BookandType(book));
    		}
    		if(book.getPhilosophy() == 1 && type.equals("philosophy")) {
    			full.add(new BookandType(book));
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
    @GetMapping("/AdminPanel")
    public String goToAdminPanel(Register register) {
    	if(register.getAdmin() == 1) {
    		return "AdminPanel";
    	}
    	else {
    		return "LoginPage";
    	}
    }
    @GetMapping("/AddBook")
    public String goToAddBook(Register register,Model model) {
    	if(register.getAdmin() == 1) {
    		model.addAttribute("book", new Book());
    		return "AddBook";
    	}
    	else {
    		return "LoginPage";
    	}
    }
    @PostMapping("/addBookFr")
    public String createBook(Register register,Book book) {
    	if(register.getAdmin() == 1) {
    		bookrepo.save(book);
        	System.out.println("Succesfully added");
    		return "BookPage";
    	}
    	else {
    		return "LoginPage";
    	}
    }
    @GetMapping("/AddAuthor")
    public String goToAddAuthor(Register register,Model model) {
    	if(register.getAdmin() == 1) {
    		model.addAttribute("book", new Author());
    		return "AddAuthor";
    	}
    	else {
    		return "LoginPage";
    	}
    }
    @PostMapping("/addAuthorFr")
    public String createAuthor(Register register,Author author) {
    	if(register.getAdmin() == 1) {
    		autrepo.save(author);
        	System.out.println("Succesfully added");
    		return "AuthorPage";
    	}
    	else {
    		return "LoginPage";
    	}
    }
}
