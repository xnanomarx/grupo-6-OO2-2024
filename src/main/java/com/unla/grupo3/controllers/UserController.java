package com.unla.grupo3.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.unla.grupo3.helpers.ViewRouteHelper;


@Controller
public class UserController {

	@GetMapping("/login")
	public String login(Model model,
						@RequestParam(name="error",required=false) String error,
						@RequestParam(name="logout", required=false) String logout) {
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		return ViewRouteHelper.USER_LOGIN;
	}

	@GetMapping("/logout")
	public String logout(Model model) {
		return ViewRouteHelper.USER_LOGOUT;
	}

	@GetMapping("/loginsuccess")
	public String loginCheck() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//user.getUserRoles()
		return "redirect:/user";
	}

	@GetMapping("/user")
	public String userHome(Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username", user.getUsername());
		return ViewRouteHelper.INDEX;
	}

	/*@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		return "register";
	}*/

	@GetMapping("/user/register")
	public String showRegistrationForm(Model model) {
		//model.addAttribute("cliente", new Cliente());
		return "user/register"; // Asegúrate de que el nombre de la plantilla es correcto
	}

}