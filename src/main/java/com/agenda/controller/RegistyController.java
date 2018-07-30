package com.agenda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.agenda.constant.ViewConstant;
import com.agenda.model.UserModel;
import com.agenda.service.UserService;

@Controller
@RequestMapping("/registro")
public class RegistyController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public String registro(Model model) {
		UserModel userModel = new UserModel();
		model.addAttribute("userModel", userModel);
		return ViewConstant.REGISTRY;
		
	}
	
	@PostMapping("/registrarse")
	public String registrase(@ModelAttribute("userModel") UserModel userModel, RedirectAttributes attributes) {
		if (userService.createUser(userModel)) {
			attributes.addFlashAttribute("registry", "Usuario Registrado Satisfactoriamente");
		}
		return "redirect:/" + ViewConstant.LOGIN;
	}

}
