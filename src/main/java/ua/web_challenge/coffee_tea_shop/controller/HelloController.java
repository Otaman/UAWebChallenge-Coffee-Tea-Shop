package ua.web_challenge.coffee_tea_shop.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class HelloController {
	@RequestMapping(value = "/hello", method = GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		return "hello";
	}

    @RequestMapping(value = "/redirect", method = GET)
    public String redirectToMain() {
        return "redirect:/pages/static/main.html";
    }

    @RequestMapping(value = "/", method = GET)
    public String forwardToMain() {
        return "forward:/pages/static/main.html";
    }
}
