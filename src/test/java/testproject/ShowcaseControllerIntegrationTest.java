package testproject;

import com.amr.project.webapp.controller.ShowcaseRestController;
import config.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureDataJpa
@AutoConfigureMockMvc
@ContextConfiguration(classes = {TestConfig.class})
public class ShowcaseControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ShowcaseRestController showcaseRestController;

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

}