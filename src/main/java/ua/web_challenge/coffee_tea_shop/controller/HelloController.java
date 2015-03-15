package ua.web_challenge.coffee_tea_shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class HelloController {
	@RequestMapping(value = "/hello", method = GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		return "hello";
	}

    @RequestMapping(value = "/", method = GET)
    public String redirectToMain() {
        return "redirect:/pages/main/main.html";
    }
}
