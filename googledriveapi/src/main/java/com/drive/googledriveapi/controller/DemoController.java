package com.drive.googledriveapi.controller;

import java.util.Base64;
import java.util.Base64.Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.drive.googledriveapi.entity.UserDetailsEntity;
import com.drive.googledriveapi.validation.UserValidation;


@Controller
public class DemoController {

	
	
	@Autowired
    UserValidation userValidation;
	
	@Autowired
	private Environment env;
	
	@InitBinder public void dataBinder(WebDataBinder binder) {
		try {
			binder.addValidators(userValidation);  

		}catch(Exception ex) { 

			System.out.println("TrainingController dataBinder : "+ex); 
			ex.printStackTrace();
		} 
	}
	
	 @GetMapping("/")
	 public String startDemo(Model model,UserDetailsEntity user){
		 model.addAttribute("user",user);
		return "login";
		 
	 }
	 
	 @PostMapping("/validate") 
		public String validate(@Valid @ModelAttribute("user")UserDetailsEntity user, BindingResult result, HttpSession session, HttpServletRequest request,Model model) {
			try {
				
				Encoder encode=Base64.getEncoder();
				
				if(result.hasErrors()) { 
					model.addAttribute(user);
					

					return "login";  

				}
				//user=userService.getUserDetails(user.getUserName());
				System.out.println(user.getUserName());
				System.out.println(env.getProperty("fullname"));
				request.getSession(false).invalidate();
				session=request.getSession(true);
				session.setAttribute("id", session.getId());
				session.setAttribute("user",env.getProperty("fullname"));  
				
				/*
				 * byte[] key= encode.encode(user.getRole().getBytes());
				 * session.setAttribute("key", key);
				 */ 
				session.setMaxInactiveInterval(60*60); 
			System.out.println("validate user method "+env.getProperty("fullname")+" user validated successfully");
				  
			}catch(Exception e) {
				System.out.println("Exception occur while login the user  "+e);
				e.printStackTrace();
			}
			return "redirect:/upload";
		}
	 @GetMapping("/upload")
	 public String uploadPage(){
		// model.addAttribute("files",files);
		return "uploadfile";
		 
	 }
}
