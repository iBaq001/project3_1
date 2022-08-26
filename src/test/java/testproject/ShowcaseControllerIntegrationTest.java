package testproject;

import com.amr.project.webapp.controller.ShowcaseRestController;
import config.TestConfig;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


@SpringBootTest
@AutoConfigureDataJpa
@AutoConfigureMockMvc
@ContextConfiguration(classes = {TestConfig.class})
public class ShowcaseControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private ShowcaseRestController showcaseRestControllercontroller;

    @Test
    public void shopListTest() throws Exception {
        mockMvc.perform(get("/shop/all")
                    .accept("application/json"))
                .andExpect(ResultMatcher.matchAll(
                        status().isOk(),
                        content().contentType("application/json")));
    }

    @Test
    public void getShopByIdTest() throws Exception {
        mockMvc.perform(get("/shop/{id}", 1L)
                    .accept("application/json"))
                .andExpect(ResultMatcher.matchAll(
                    status().isOk(),
                    content().contentType("application/json")));
    }

    @Test
    public void findOne() throws Exception {
        mockMvc.perform(get("/shop/1"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test 
    public void addShop() throws Exception {
        mockMvc.perform(post("/shop"))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json("{'name':'Test','description':'TestЛога - лучшие тесты пенной продукции', 'email' :'TestЛога@testmail.ru', 'phone' :'+7-800-875-35-35', 'rating':'9.9'}"))
            .andExpect(status().isOk());
    }

    @Test
    public void deleteShop() throws Exception {
        mockMvc.perform(delete("/shop/3"))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }

}
