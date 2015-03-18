package ua.web_challenge.coffee_tea_shop.persistence;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.web_challenge.coffee_tea_shop.entity.Drink;
import ua.web_challenge.coffee_tea_shop.persistence.generic.JpaGenericDaoImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.springframework.transaction.annotation.Propagation.SUPPORTS;

/**
 * Created on 15.03.2015
 *
 * @author Bohdan Vanchuhov
 */
@Repository("drinkDao")
@Transactional(propagation = SUPPORTS, readOnly = true)
public class JpaDrinkDao extends JpaGenericDaoImpl<Drink, Integer> implements DrinkDao {
    @PersistenceContext
    private EntityManager entityManager;
}
