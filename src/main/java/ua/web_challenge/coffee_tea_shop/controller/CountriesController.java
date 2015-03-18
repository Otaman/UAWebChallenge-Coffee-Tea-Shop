package ua.web_challenge.coffee_tea_shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.web_challenge.coffee_tea_shop.persistence.CountryDao;
import ua.web_challenge.coffee_tea_shop.entity.Country;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created on 16.03.2015
 *
 * @author Bohdan Vanchuhov
 */
@Controller
public class CountriesController {
    @Autowired
    private CountryDao countryDao;

    @RequestMapping(value = "/countries", method = GET)
    public String counties(Model model) {
        List<Country> countries = countryDao.findAll();

        model.addAttribute("countries", countries);

        return "countries";
    }
}
