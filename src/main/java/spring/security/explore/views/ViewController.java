package spring.security.explore.views;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;
import java.util.function.Supplier;

@Controller
@RequestMapping("/view")
public class ViewController {

	@GetMapping("/login")
	String login(@RequestParam Optional<String> redirectUrl, Model model) {
		Supplier<String> defaultRedirectUrl = () -> ServletUriComponentsBuilder
				.fromCurrentContextPath()
				.build()
				.toUriString();
		model.addAttribute("redirectUrl", redirectUrl.orElseGet(defaultRedirectUrl));
		return "login";
	}

}
