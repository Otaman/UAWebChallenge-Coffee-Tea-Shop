package ua.web_challenge.coffee_tea_shop.persistence;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.web_challenge.coffee_tea_shop.entity.Country;
import ua.web_challenge.coffee_tea_shop.persistence.generic.JpaGenericDaoImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.ManagedType;
import java.util.List;

import static org.springframework.transaction.annotation.Propagation.SUPPORTS;

/**
 * Created on 16.03.2015
 *
 * @author Bohdan Vanchuhov
 */
@Repository("countryDao")
@Transactional(propagation = SUPPORTS, readOnly = true)
public class JpaCountryDao extends JpaGenericDaoImpl<Country, Integer> implements CountryDao {
    @Override
    public List<Country> findAll() {
        return findRange(0);
    }
}
