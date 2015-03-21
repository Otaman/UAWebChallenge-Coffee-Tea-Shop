package ua.web_challenge.coffee_tea_shop;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
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

import static com.github.springtestdbunit.assertion.DatabaseAssertionMode.NON_STRICT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.annotation.DirtiesContext.*;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.*;

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
        DbUnitTestExecutionListener.class})
@DatabaseSetup("/datasets/country-data.xml")
public class CountryDaoTest {
    @Autowired
    private CountryDao countryDao;

    @Test
    public void findAll() throws Exception {
        List<Country> countries = countryDao.findAll();
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

    @Test
    public void findById() throws Exception {
        Country country = countryDao.findById(1);

        assertThat(country.getName(), is("Ukraine"));
    }

    @Test
    @ExpectedDatabase(value = "/datasets/country-data-after-insert.xml", assertionMode = NON_STRICT)
    public void addCountry() throws Exception {
        Country country = new Country();
        country.setName("Spain");

        countryDao.add(country);
    }

    @Test
    public void findByName() throws Exception {
        Country country = countryDao.findByName("Ukraine");

        assertThat(country.getName(), is("Ukraine"));
    }

    @Test
    @ExpectedDatabase(value = "/datasets/country-data-after-update.xml", assertionMode = NON_STRICT)
    public void updateCountry() throws Exception {
        Country country = countryDao.findById(4);
        country.setName("Greece");
        countryDao.update(country);
    }

    @Test
    @ExpectedDatabase(value = "/datasets/country-data-after-delete.xml", assertionMode = NON_STRICT)
    public void deleteCountry() throws Exception {
        Country country = countryDao.findById(4);
        countryDao.delete(country);
    }
}
