package hr.fer.opp.projekt.audioVodic.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import hr.fer.opp.projekt.audioVodic.Util;
import hr.fer.opp.projekt.audioVodic.model.Role;
import hr.fer.opp.projekt.audioVodic.model.User;
import hr.fer.opp.projekt.audioVodic.service.NotificationService;
import hr.fer.opp.projekt.audioVodic.service.RoleService;
import hr.fer.opp.projekt.audioVodic.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private NotificationService notificationService;
	
	@RequestMapping("/confirmRegistration/{id}")
	public ModelAndView confirmRegistration(@PathVariable("id") int id) {
		User user = userService.getUser(id);
		user.setRole(roleService.getRoleByName("Registrirani korisnik"));
		userService.addUser(user);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("user",user);
		mv.setViewName("welcome");
		return mv;
	}

	@RequestMapping("/registrationForm")
	public String showRegistrationForm() {
		return "registrationForm";
	}

	//Jurenec --backend za odvodjenje na registraciju
	//Jurenec - odnosi se na provjeru postoji li duplikat email adrese u bazi podataka
	
	@RequestMapping(value = "/registrationForm", method = RequestMethod.POST)
	public String registrationValidation(HttpServletRequest req, HttpServletResponse res) {

		String firstNameError = "";
		String lastNameError = "";
		String usernameError = "";
		String emailError = "";
		String passwordError = "";
		
		boolean firstNameHasError = false;
		boolean lastNameHasError = false;
		boolean usernameHasError = false;
		boolean emailHasError = false;
		boolean passwordHasError = false;

		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String username = req.getParameter("username");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		req.setAttribute("firstName", firstName);
		req.setAttribute("lastName", lastName);
		req.setAttribute("username", username);
		req.setAttribute("email", email);

		//firstName
		if (firstName == null || firstName.isEmpty() || !Util.checkIsAlphabetic(firstName)) {
			firstNameError = "Možete upotrebljavati samo slova";
			firstNameHasError = true;
			req.setAttribute("firstNameError", firstNameError);
			req.setAttribute("firstNameHasError", firstNameHasError);
		}

		//lastName
		if (lastName == null || lastName.isEmpty() || !Util.checkIsAlphabetic(lastName)) {
			lastNameError = "Možete upotrebljavati samo slova";
			lastNameHasError = true;
			req.setAttribute("lastNameError", lastNameError);
			req.setAttribute("lastNameHasError", lastNameHasError);
		}

		//username
		if (username == null || username.isEmpty() || !Util.checkUsername(username)) {
			usernameError = "Možete upotrebljavati samo slova i brojeve";
			usernameHasError = true;
			req.setAttribute("usernameError", usernameError);
			req.setAttribute("usernameHasError", usernameHasError);
		}
		if (!usernameHasError) {
			User user = userService.getUserByUsername(username);
			if (user != null) {
				usernameError = "Korisničko ime je zauzeto";
				usernameHasError = true;
				req.setAttribute("usernameError", usernameError);
				req.setAttribute("usernameHasError", usernameHasError);
			}
		}

		//email
		if (email == null || email.isEmpty() || !Util.checkMail(email)) {
			emailError = "Možete upotrebljavati samo slova, brojeve i točke";
			emailHasError = true;
			req.setAttribute("emailError", emailError);
			req.setAttribute("emailHasError", emailHasError);
		}

		//password
		if (password == null || password.isEmpty()) {
			passwordError = "Unesite lozinku";
			passwordHasError = true;
			req.setAttribute("passwordError", passwordError);
			req.setAttribute("passwordHasError", passwordHasError);
		}
		
		if (firstNameHasError || lastNameHasError || usernameHasError || emailHasError || passwordHasError) {
			return "registrationForm";
		}
		
		Role role = roleService.getRoleByName("Neregistrirani korisnik");
		if (role == null) {
			System.out.println("greska");
		}
		
		User user = new User(username, firstName, lastName, email, Util.sha(req.getParameter("password")),role);
		userService.addUser(user);
		
		try {
			notificationService.sendNotification(user);
		}catch(MailException exc) {
			//ignore
		}

		return "redirect:/museumObjects";
	}
	
	@RequestMapping("/logInForm")
	public String showLogInForm() {
		return "logIn";
	}
	
	@RequestMapping(value = "/logInForm", method = RequestMethod.POST)
	public String logInValidation(HttpServletRequest req, HttpServletResponse res) {
		
		String logInError = "";
		
		boolean logInHasError = false;

		String username = req.getParameter("username");
		String passwordHash = Util.sha(req.getParameter("password"));
		
		req.setAttribute("username", username);
		
		User user = userService.getUserByUsername(username);
		
		if (user == null || !((user.getPasswordHash()).equals(passwordHash))) {
			logInHasError = true;
			logInError = "Neispravno korisničko ime ili lozinka";
			req.setAttribute("logInError", logInError);
			req.setAttribute("logInHasError", logInHasError);
			return "logIn";
		}
		if ((user.getRole().getName().equals("Neregistrirani korisnik"))){
			logInHasError = true;
			logInError = "Potvrdite registraciju putem mail adrese";
			req.setAttribute("logInError", logInError);
			req.setAttribute("logInHasError", logInHasError);
			return "logIn";
		}
		
		return  "redirect:/museumObjects";
	}

}
