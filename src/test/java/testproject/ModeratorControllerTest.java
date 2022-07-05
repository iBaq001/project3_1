package testproject;

import com.amr.project.webapp.controller.ReviewController;
import config.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class})
@AutoConfigureMockMvc
@AutoConfigureDataJpa
class ModeratorControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ReviewController reviewController;

    @Test
    void pageTest() throws Exception {
        mockMvc.perform(get("/api/reviews/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getReviewsDtoTest() throws Exception {
        mockMvc.perform(get("/api/reviews/")
                        .accept("application/json"))
                .andExpect(ResultMatcher.matchAll(
                        status().isOk(),
                        content().contentType("application/json")));
    }
}
