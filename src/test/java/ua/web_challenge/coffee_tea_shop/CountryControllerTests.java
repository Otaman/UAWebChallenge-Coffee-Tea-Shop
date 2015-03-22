package ua.web_challenge.coffee_tea_shop;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static com.github.springtestdbunit.assertion.DatabaseAssertionMode.NON_STRICT;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({
        "classpath:/spring/test-application-context.xml"
})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public class CountryControllerTests {
    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void countryById() throws Exception {
        mockMvc.perform(get("/country/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("country"));
    }

    @Test
    public void countryById_wrongId() throws Exception {
        mockMvc.perform(get("/country/10"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void countryToJson() throws Exception {
        mockMvc.perform(get("/country/json/1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Ukraine")));
    }

    @Test
    public void countryToJson_wrongId() throws Exception {
        mockMvc.perform(get("/country/json/10"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DatabaseSetup("/datasets/country-data.xml")
    @ExpectedDatabase(value = "/datasets/country-data-after-insert.xml", assertionMode = NON_STRICT)
    public void addJsonCountry() throws Exception {
        mockMvc.perform(post("/country/json/add")
                        .content("{\"name\":\"Spain\"}")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());
    }
}
