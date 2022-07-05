package testproject;

import com.amr.project.webapp.controller.SalesReportRestController;
import config.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureDataJpa
@AutoConfigureMockMvc
@ContextConfiguration(classes = {TestConfig.class})
public class SalesReportRestControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SalesReportRestController controller;

    @Test
    public void contextTest() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void EmptyPageTest() throws Exception {
        this.mockMvc.perform(get("/shopSalesReport/{shopId}", 1L))
                .andDo(print())
                .andExpect(view().name("shopSalesReport"))
                .andExpect(model().attribute("salesHistory", empty()))
                .andExpect(model().size(4))
                .andExpect(status().isOk());
    }

    @Test
    public void getResultRegularDateTest() throws Exception {
        this.mockMvc.perform(get("/shopSalesReport/{shopId}", 1L)
                        .param("typeOfFilter", "HalfYear")
                        .param("itemsDto","Хадыженское")
                        .param("itemsDto","Сидрoff")
                        .param("typeOfDateSort","Old")
                )
                .andDo(print())
                .andExpect(model().attributeExists("salesHistory"))
                .andExpect(view().name("shopSalesReport"))
                .andExpect(model().attribute("salesHistory", notNullValue()))
                .andExpect(model().attribute("salesHistory", hasSize(4)))
                .andExpect(status().isOk());
    }

    @Test
    public void getResultFromDateToDateTest() throws Exception {
        this.mockMvc.perform(get("/shopSalesReport/{shopId}", 1L)
                        .param("typeOfFilter", "FromDateToDate")
                        .param("date1", "2022-06-01")
                        .param("date2", "2022-06-30")
                        .param("itemsDto","Хадыженское")
                        .param("itemsDto","Сидрoff")
                        .param("typeOfDateSort","New")
                )
                .andDo(print())
                .andExpect(model().attributeExists("salesHistory"))
                .andExpect(view().name("shopSalesReport"))
                .andExpect(model().attribute("salesHistory", notNullValue()))
                .andExpect(model().attribute("salesHistory", hasSize(2)))
                .andExpect(status().isOk());
    }

}
