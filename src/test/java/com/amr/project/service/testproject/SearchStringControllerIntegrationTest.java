package com.amr.project.service.testproject;


import com.amr.project.webapp.controller.SearchStringController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class SearchStringControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private SearchStringController search;


    @Test
    public void testFindItem() throws Exception {
        this.mvc.perform(get("/search/itemList/{name}", "ff"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].name").value("Сидрoff"));
    }

    @Test
    public void testFindShop() throws Exception {
        this.mvc.perform(get("/search/shopList/{name}", "kam"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].name").value("RibkaMoya"));
    }
    @Test
    public void testNoContent() throws Exception {
        this.mvc.perform(get("/search/shopList/{name}", "scxxvrs"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

}