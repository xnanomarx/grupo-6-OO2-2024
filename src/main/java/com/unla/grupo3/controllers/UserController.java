package com.unla.grupo3.controllers;

import com.unla.grupo3.entities.UserRole;
import com.unla.grupo3.services.implementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.unla.grupo3.helpers.ViewRouteHelper;

import java.util.HashSet;
import java.util.Set;


@Controller
public class UserController {

	@Autowired
	private UserService serviceUser;

	@Autowired
	private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

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
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		// Verificar si el usuario tiene el rol ADMIN
		if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
			return ViewRouteHelper.INDEX;
		}

		// Verificar si el usuario tiene el rol USER
		if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
			return ViewRouteHelper.USER;
		}

		// Por defecto, redirigir a una página de error o a la página principal
		return "redirect:/";
	}

	@GetMapping("/user")
	public String userHome(Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username", user.getUsername());
		return ViewRouteHelper.USER;
	}

	@GetMapping("/formregister")
	public String showRegistrationForm(Model model) {
		return "user/register";
	}

	@PostMapping("/guardarregister")
	public String saveUser(@RequestParam("username")String username,
						   @RequestParam("password")String password) {
		com.unla.grupo3.entities.User user = new com.unla.grupo3.entities.User(username,password,true);
		user.setPassword(passwordEncoder.encode(password));

		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole("ROLE_USER");

		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(userRole);

		user.setUserRoles(userRoles);

		userService.guardarUser(user);
		userService.guardarUserRoles(userRole);

		return "redirect:/login";
	}
}
