package ua.web_challenge.coffee_tea_shop;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ua.web_challenge.coffee_tea_shop.entity.Country;
import ua.web_challenge.coffee_tea_shop.persistence.CountryDao;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created on 17.03.2015
 *
 * @author Bohdan Vanchuhov
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "file:src/main/webapp/WEB-INF/spring/persistence-context.xml",
        "classpath:/spring/test-datasource-context.xml"
})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager")
@DatabaseSetup("/datasets/country-data.xml")
public class CountryDaoTest {
    @Autowired
    private CountryDao countryDao;

    @Test
    public void addCountry() throws Exception {
        Country country = new Country();
        country.setName("Spain");

        countryDao.add(country);

        List<Country> countries = countryDao.findAll();
        List<String> countryNames = getCountryNames(countries);

        assertThat(countryNames, is(hasItem("Spain")));
    }

    @Test
    public void findAll() throws Exception {
        final List<Country> countries = countryDao.findAll();
        List<String> countryNames = getCountryNames(countries);

        assertThat(countryNames, is(containsInAnyOrder(
                "Ukraine",
                "USA",
                "Germany",
                "France"
        )));
    }

    private List<String> getCountryNames(List<Country> countries) {
        return countries.stream()
                .map(Country::getName)
                .collect(Collectors.toList());
    }
}
