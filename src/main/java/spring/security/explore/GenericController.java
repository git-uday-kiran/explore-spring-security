package spring.security.explore;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

@CrossOrigin
@RestController
public class GenericController {

	@GetMapping
	String get(@RequestParam(defaultValue = "false") boolean redirect, HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (redirect) {
			String currentUrl = request.getRequestURL().toString();
			String loginView = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/view/login")
					.queryParam("redirect", currentUrl)
					.build()
					.toUriString();
			System.out.println("redirecting to " + loginView);
			response.sendRedirect(loginView);
		}
		return "Hello World";
	}

	@PostMapping("/login")
	void login(@ModelAttribute LoginForm loginForm, HttpServletResponse response) throws IOException {
		System.out.println("Logged In :) " + loginForm);
		response.sendRedirect(loginForm.redirectUrl);
	}

	record LoginForm(String username, String password, String redirectUrl) {
	}

}
