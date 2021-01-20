package com.drive.googledriveapi.validation;

import java.util.Base64;
import java.util.Base64.Decoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.drive.googledriveapi.entity.UserDetailsEntity;

@Component
public class UserValidation implements Validator{
	
	///private static Logger logger = Logger.getLogger(UserValidation.class);

	@Autowired
	private Environment env;
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UserDetailsEntity.class.isAssignableFrom(clazz); 
	}

	
	@Override
	public void validate(Object target, Errors errors) {
		UserDetailsEntity user = (UserDetailsEntity) target;
		Decoder decoder = Base64.getDecoder();
		try {
		
			
			byte[] decodedByte = decoder.decode(user.getHiddenField());
			String decodedString = new String(decodedByte);
			
			decodedString=decodedString!=null?decodedString:"";
			
			if(decodedString.trim()=="") {
				errors.rejectValue("passWord", "login.password");
			}
			
			 
			if(user.getUserName()==null || !user.getUserName().matches("^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$")) {  
				errors.rejectValue("userName", "login.userName");
			}
			System.out.println("frontend:  "+user.getUserName());
			System.out.println(env.getProperty("uasername"));
			System.out.println(decodedString);
			if(!user.getUserName().equals(env.getProperty("uasername"))) {
				errors.rejectValue("userName", "login.userName");
			}
			if(!decodedString.equals(env.getProperty("password"))) {
				errors.rejectValue("passWord", "login.password");
			}
			
		}catch(Exception e) {
			//logger.error("Ecxeption occur while validating user   ",e);
			System.out.println("Ecxeption occur while validating user   "+e);
			e.printStackTrace();
			errors.rejectValue("userName", "login.userName");
		}
		
	}

}
