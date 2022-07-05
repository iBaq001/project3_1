package testproject;

import com.amr.project.ProjectApplication;
import com.amr.project.model.entity.*;
import com.amr.project.service.abstracts.FavoriteService;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.junit5.api.DBRider;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProjectApplication.class})
@AutoConfigureMockMvc
@DBRider
@DBUnit(allowEmptyFields = true)
//@DbUnitConfiguration(databaseConnection = "h2DataSource")
//@TestPropertySource(
//        locations = "classpath:/application-integrationtest.properties")
class FavoriteRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private FavoriteService favoriteService;

    @BeforeTest
    void setUp() {
    }

    @AfterTest
    void tearDown() {
    }

    @Test
    @WithUserDetails("user1")
    void getFavoriteDto() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/favorite/")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithUserDetails("user1")
    void removeFavoriteShopDtoById() throws Exception {
        long exitingShopId = 1;

        // get current user
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // assertIfExist
        assertTrue(favoriteService.getFavorite(currentUser.getId()).getShops().stream().anyMatch(e -> e.getId() == exitingShopId));

        // act
        mvc.perform(MockMvcRequestBuilders.delete("/favorite/shop/remove/").param("shopId", Long.toString(exitingShopId))).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json("{\"success\":1}"));

        // assertIfNotExist
        assertFalse(favoriteService.getFavorite(currentUser.getId()).getShops().stream().anyMatch(e -> e.getId() == exitingShopId));
    }

    @Test
    @WithUserDetails("user1")
    void removeFavoriteItemDtoById() throws Exception {
        long exitingItemId = 1;

        // get current user
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // assertIfExist
        assertTrue(favoriteService.getFavorite(currentUser.getId()).getShops().stream().anyMatch(e -> e.getId() == exitingItemId));

        // act
        mvc.perform(MockMvcRequestBuilders.delete("/favorite/item/remove/").param("itemId", Long.toString(exitingItemId))).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json("{\"success\":1}"));

        // assertIfNotExist
        assertFalse(favoriteService.getFavorite(currentUser.getId()).getItems().stream().anyMatch(e -> e.getId() == exitingItemId));
    }

    @Test
    @WithUserDetails("user2")
    void addFavoriteShopDtoById() throws Exception {
        long nonExitingShopId = 2;

        // get current user
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // assertIfNotExist
        assertFalse(favoriteService.getFavorite(currentUser.getId()).getShops().stream().anyMatch(e -> e.getId() == nonExitingShopId));

        //act
        mvc.perform(MockMvcRequestBuilders.post("/favorite/shop/add/").param("shopId", Long.toString(nonExitingShopId))).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json("{\"success\":1}"));

        // assertIfExist
        assertTrue(favoriteService.getFavorite(currentUser.getId()).getShops().stream().anyMatch(e -> e.getId() == nonExitingShopId));
    }

    @Test
    @WithUserDetails("user2")
    void addFavoriteItemDtoById() throws Exception {
        long nonExitingItemId = 3;

        // get current user
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // assertIfNotExist
        assertFalse(favoriteService.getFavorite(currentUser.getId()).getItems().stream().anyMatch(e -> e.getId() == nonExitingItemId));

        //act
        mvc.perform(MockMvcRequestBuilders.post("/favorite/item/add/").param("itemId", Long.toString(nonExitingItemId))).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json("{\"success\":1}"));

        // assertIfExist
        assertTrue(favoriteService.getFavorite(currentUser.getId()).getItems().stream().anyMatch(e -> e.getId() == nonExitingItemId));
    }
}