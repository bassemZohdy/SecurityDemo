package demo.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import demo.entity.Activation;
import demo.entity.User;
import demo.service.UserRegisterService;

@Controller
public class MainController {

	@Autowired
	private UserRegisterService regService;

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@Valid User user, BindingResult result) {
		regService.register(user);
		return "redirect:/login";
	}
	
	@RequestMapping(value = "/activation/{token}", method = RequestMethod.GET)
	public String activate(@PathVariable String token){
		regService.activate(token);
		return "redirect:/login";
	}
	

}
