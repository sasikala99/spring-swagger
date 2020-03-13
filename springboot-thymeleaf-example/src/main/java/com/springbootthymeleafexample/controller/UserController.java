package com.springbootthymeleafexample.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.springbootthymeleafexample.model.User;
import com.springbootthymeleafexample.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping(value="/signup")
	public String signup(User user){
		return "add-user";
	}
	
	@GetMapping(value="/")
	public String home(User user){
		return "index";
	}
	
	@PostMapping(value="/adduser")
	public String adduser(@Valid User user,BindingResult result,Model model){
		if(result.hasErrors()){
			return "add-user";
		} else{
			userRepository.save(user);
			model.addAttribute("users", userRepository.findAll());
			return "index";
		}
	}
	
	@GetMapping(value="/edit/{userid}")
	public String edituser(@PathVariable("userid") Integer userid,Model model){
		User user = userRepository.findOne(userid);
		model.addAttribute("user", user);
		return "update-user";
	}
	
	@PostMapping(value="/update/{userid}")
	public String updateuser(@PathVariable("userid") Integer userid,@Valid User user,BindingResult result,Model model){
		if(result.hasErrors()){
			user.setUserid(userid);
			return "update-user";
		} else{
			userRepository.save(user);
			model.addAttribute("users", userRepository.findAll());
			return "index";
		}
	}
	
	@GetMapping(value="/delete/{userid}")
	public String deleteuser(@PathVariable("userid") Integer userid,Model model){
		User user = userRepository.findOne(userid);
		userRepository.delete(user);
		model.addAttribute("users", userRepository.findAll());
		return "index";
	}
}
