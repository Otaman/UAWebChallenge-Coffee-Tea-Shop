package ua.web_challenge.coffee_tea_shop.persistence;

import ua.web_challenge.coffee_tea_shop.entity.Country;
import ua.web_challenge.coffee_tea_shop.persistence.generic.GenericDao;

import java.util.List;

/**
 * Created on 16.03.2015
 *
 * @author Bohdan Vanchuhov
 */
public interface CountryDao extends GenericDao<Country, Integer> {
    List<Country> findAll();
}
