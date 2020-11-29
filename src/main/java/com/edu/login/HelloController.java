package com.edu.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

   @RequestMapping("/login")
   public String login(Model model) {
	 if(!model.containsAttribute("message")) {
		 model.addAttribute("message","Please login account admin"); 
	 }
      return "login";
   }
   
   @RequestMapping("/index")
   public String index() {
      return "index";
   }
	
}
