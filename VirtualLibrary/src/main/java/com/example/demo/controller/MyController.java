package com.example.demo.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.example.demo.bean.TermRepo;
import com.example.demo.bean.Terms;
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
	
	@Autowired
	private TermRepo termrepo;

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
    	    			register.setLid(oneblog.getLid());
    	    			register.setAdmin(1);
    	    			System.out.println("Succesful login");
    	    			return "AdminPanel";
    				}
    			}
    			if(oneblog.getTerm() > 2) {
    				return "LoginPage";
    			}
    			register.setFirst(oneblog.getFirstname());
    			register.setSecond(oneblog.getLastname());
    			register.setPhone(oneblog.getPhonenumber());
    			register.setTerm(oneblog.getTerm());
    			register.setLid(oneblog.getLid());
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
    		if(register.getAdmin() == 1) {
    			return "AdminPanel";
    		}
    		else {
    			return "Panel";
    		}
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
    		if(book.getBorrower() == 0) {
    			full.add(new BookandType(book));
    		}
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
    		if(book.getBorrower() == 0) {
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
    		model.addAttribute("author", new Author());
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
    @GetMapping("/UpdateBook")
    public String goToUpdateBook(Register register,Model model) {
    	if(register.getAdmin() == 1) {
    		model.addAttribute("book", new Book());
    		model.addAttribute("books", bookrepo.findAll());
    		return "UpdateBook";
    	}
    	else {
    		return "LoginPage";
    	}
    }
    @PostMapping("/updateBookFr")
    public String updateBook(Register register,Book book,Book chosen) {
    	if(register.getAdmin() == 1) {
    		book.setBid(chosen.getBid());
    		bookrepo.save(book);
    		return "BookPage";
    	}
    	else {
    		return "LoginPage";
    	}
    }
    @GetMapping("/UpdateAuthor")
    public String goToUpdateAuthor(Register register,Model model) {
    	if(register.getAdmin() == 1) {
    		model.addAttribute("author", new Author());
    		model.addAttribute("authors", autrepo.findAll());
    		return "UpdateAuthor";
    	}
    	else {
    		return "LoginPage";
    	}
    }
    @PostMapping("/updateAuthorFr")
    public String updateAuthor(Register register,Author author,Author chosen) {
    	if(register.getAdmin() == 1) {
    		author.setAid(chosen.getAid());
    		autrepo.save(author);
    		return "AuthorPage";
    	}
    	else {
    		return "LoginPage";
    	}
    }
    @GetMapping("/borrowBook/{id}")
    public String borrowBook(Register register,@PathVariable(name="id")int id) {
    	List<Book> books = bookrepo.findAll();
    	for(Book book : books) {
    		if(book.getBid() == id) {
    			book.setBorrower(register.getLid());
    			bookrepo.save(book);
    		}
    	}
		return "Panel";
    }
    @GetMapping("/Borrowed")
    public String goToBorrowed(Register register,Model model) {
    	if(register.getEmail() == null) {
    		return "LoginPage";
    	}
    	List<Book> books = bookrepo.findAll();
    	List<BookandType> full = new ArrayList<>();
    	for(Book book : books) {
    		if(book.getBorrower() == register.getLid()) {
    			full.add(new BookandType(book));
    		}
    	}
    	model.addAttribute("books",full);
    	return "Borrowed";
    }
    @GetMapping("/returnBook/{id}")
    public String returnBook(Register register,@PathVariable(name="id")int id) {
    	if(register.getEmail() == null) {
    		return "LoginPage";
    	}
    	List<Book> books = bookrepo.findAll();
    	for(Book book : books) {
    		if(book.getBid() == id) {
    			book.setBorrower(0);
    			bookrepo.save(book);
    		}
    	}
		return "Panel";
    }
    @GetMapping("/Removal")
    public String goToRemoval(Register register,Model model) {
    	if(register.getAdmin() == 1) {
    		model.addAttribute("author", new Author());
    		model.addAttribute("authors", autrepo.findAll());
    		model.addAttribute("book", new Book());
    		model.addAttribute("books", bookrepo.findAll());
    		return "Removal";
    	}
    	else {
    		return "LoginPage";
    	}
    }
    @PostMapping("/removeAuthor")
    public String removeAuthor(Register register, Author chosen) {
    	autrepo.deleteById(chosen.getAid());
    	return "AdminPanel";
    }
    @PostMapping("/removeBook")
    public String removeBook(Register register, Book chosen) {
    	bookrepo.deleteById(chosen.getBid());
    	return "AdminPanel";
    }
    
    @GetMapping("/PunishUser")
    public String punishUser(Register register, Model model) {
    	if(register.getAdmin() == 0) {
    		return "LoginPage";
    	}
    	model.addAttribute(new Terms());
    	return "PunishUser";
    }
    @PostMapping("/punish")
    public String punish(Register register, Terms terms) {
    	if(register.getAdmin() == 0) {
    		return "LoginPage";
    	}
    	LocalDateTime e = LocalDateTime.now();
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	String fe = formatter.format(e);
    	List<User> users = repo.findAll();
    	for(User user : users) {
    		if(user.getLid() == terms.getUser()) {
    			user.setTerm(user.getTerm()+1);
    			repo.save(user);
    		}
    	}
    	terms.setDate(fe);
    	terms.setAdmin(register.getLid());
    	termrepo.save(terms);
    	return "PunishUser";
    }
    @PostMapping("/searchBook")
    public String getSearchedBook(Register register,Model model,@RequestParam(value="type", required=true) String type) {
    	if(register.getEmail() == null) {
    		return "LoginPage";
    	}
    	List<BookandType> full = new ArrayList<>();
    	List<Book> books = bookrepo.findAll();
    	for(Book book : books) {
    		if(book.getBorrower() == 0) {
    			if(book.getName().toLowerCase().contains(type.toLowerCase())) {
    				full.add(new BookandType(book));
    			}
    		}
    	}
    	model.addAttribute("books",full);
    	return "BookPage";
    }
}
