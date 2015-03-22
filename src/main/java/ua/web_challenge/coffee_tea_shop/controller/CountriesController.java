package ua.web_challenge.coffee_tea_shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.web_challenge.coffee_tea_shop.persistence.CountryDao;
import ua.web_challenge.coffee_tea_shop.entity.Country;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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

    @RequestMapping(value = "/country/{id}", method = GET)
    public String country(@PathVariable("id") int id, Model model) {
        Country country = countryDao.findById(id);

        checkCountry(country);

        model.addAttribute("country", country);
        return "country";
    }

    private void checkCountry(Country country) {
        if (country == null) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/country/json/{id}", method = GET)
    public @ResponseBody Country jsonCountry(@PathVariable("id") int id) {
        Country country = countryDao.findById(id);

        checkCountry(country);

        return country;
    }

    @RequestMapping(value = "/country/json/add", method = POST)
    public String addJsonCountry(@RequestBody Country country) {
        countryDao.add(country);

        return "forward:/countries";
    }
}
