package com.example.demo;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PersonController {
	
	@GetMapping
	String getPeople(@RequestParam(required = false) String loginText,Model model) {
		model.addAttribute("something","This is what we want to see");
		model.addAttribute("them", Arrays.asList(
				new Person("Mustafar",21),
				new Person("Hidayed",21)
		));
		model.addAttribute("called", loginText);
		return "people";
	}
	
	
}
