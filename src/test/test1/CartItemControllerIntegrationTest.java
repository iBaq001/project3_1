package test1;

import com.amr.project.service.abstracts.CartItemService;

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


public class CartItemControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CartItemService cartItemService;

    @Test
    public void addItemToCartTest() throws Exception {
        mockMvc.perform(post("/cart/addItem")
                        .param("itemId", "1")
                        .param("shopId", "1")
                        .param("quantity", "200")
                        .accept("text/html; charset=utf-8"))
                .andExpect(status().isOk());
    }

    @Test
    public void getCartItemDTOsTest() throws Exception {
        mockMvc.perform(get("/cart/")
                        .accept("application/json"))
                .andExpect(ResultMatcher.matchAll(
                        status().isOk(),
                        content().contentType("application/json")));
    }

    @Test
    public void editQuantityInTheCartTest() throws Exception {

        mockMvc.perform(post("/cart/addItem")
                        .param("itemId", "1")
                        .param("shopId", "1")
                        .param("quantity", "200")
                        .accept("text/html; charset=utf-8")).
                andExpect(status().isOk());

        mockMvc.perform(post("/cart/addItem")
                        .param("itemId", "2")
                        .param("shopId", "1")
                        .param("quantity", "200")
                        .accept("text/html; charset=utf-8")).
                andExpect(status().isOk());

        mockMvc.perform(put("/cart/")
                        .contentType("application/json")
                        .content("[\n" +
                                "  {\n" +
                                "    \"id\": 1,\n" +
                                "    \"itemId\": 1,\n" +
                                "    \"quantity\": 5555,\n" +
                                "    \"shopId\": 1\n" +
                                "  },\n" +
                                "  {\n" +
                                "    \"id\": 2,\n" +
                                "    \"itemId\": 2,\n" +
                                "    \"quantity\": 9999,\n" +
                                "    \"shopId\": 1\n" +
                                "  }\n" +
                                "]")
                        .accept("text/html; charset=utf-8"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteCartItemTest() throws Exception {
        mockMvc.perform(post("/cart/addItem")
                        .param("itemId", "1")
                        .param("shopId", "1")
                        .param("quantity", "200")
                        .accept("text/html; charset=utf-8"))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/cart/")
                        .param("cartItemId", "5")
                        .accept("text/html; charset=utf-8"))
                .andExpect(status().isOk());
    }

}
