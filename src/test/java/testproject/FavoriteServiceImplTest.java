package testproject;

import com.amr.project.ProjectApplication;
import com.amr.project.model.dto.FavoriteDto;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.FavoriteService;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.junit5.api.DBRider;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProjectApplication.class})
@AutoConfigureMockMvc
@DBRider
@DBUnit(allowEmptyFields = true)
//@DbUnitConfiguration(databaseConnection = "h2DataSource")
//@TestPropertySource(
//        locations = "classpath:/application-integrationtest.properties")
class FavoriteServiceImplTest {

    @Autowired
    private FavoriteService favoriteService;

    @Test
    @WithUserDetails("user1")
    void getFavorite() {
        // get current user
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //act
        FavoriteDto favorite = favoriteService.getFavorite(currentUser.getId());

        //assert
        assertEquals(favorite.getItems().size(), 3);
        assertEquals(favorite.getShops().size(), 2);
    }

    @Test
    @WithUserDetails("user2")
    void addFavoriteItemById() {
        long nonExistingItem = 3;

        // get current user
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // assertIfNotExist
        assertFalse(favoriteService.getFavorite(currentUser.getId()).getItems().stream().anyMatch(e -> e.getId() == nonExistingItem));

        // act
        favoriteService.addFavoriteItemById(currentUser.getId(), nonExistingItem);

        // assertIfExist
        assertTrue(favoriteService.getFavorite(currentUser.getId()).getItems().stream().anyMatch(e -> e.getId() == nonExistingItem));
    }

    @Test
    @WithUserDetails("user2")
    void addFavoriteShopById() {
        long nonExistingShop = 2;

        // get current user
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // assertIfNotExist
        assertFalse(favoriteService.getFavorite(currentUser.getId()).getShops().stream().anyMatch(e -> e.getId() == nonExistingShop));

        // act
        favoriteService.addFavoriteShopById(currentUser.getId(), nonExistingShop);

        // assertIfExist
        assertTrue(favoriteService.getFavorite(currentUser.getId()).getShops().stream().anyMatch(e -> e.getId() == nonExistingShop));
    }

    @Test
    @WithUserDetails("user1")
    void removeFavoriteItemById() {
        long existingItem = 2;

        // get current user
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // assertExist
        assertTrue(favoriteService.getFavorite(currentUser.getId()).getItems().stream().anyMatch(e -> e.getId() == existingItem));

        // act
        favoriteService.removeFavoriteItemById(currentUser.getId(), existingItem);

        // // assertIfNotExist
        assertFalse(favoriteService.getFavorite(currentUser.getId()).getItems().stream().anyMatch(e -> e.getId() == existingItem));
    }

    @Test
    @WithUserDetails("user1")
    void removeFavoriteShopById() {
        long existingShop = 1;

        // get current user
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // assertExist
        assertTrue(favoriteService.getFavorite(currentUser.getId()).getShops().stream().anyMatch(e -> e.getId() == existingShop));

        // act
        favoriteService.removeFavoriteShopById(currentUser.getId(), existingShop);

        // // assertIfNotExist
        assertFalse(favoriteService.getFavorite(currentUser.getId()).getShops().stream().anyMatch(e -> e.getId() == existingShop));
    }
}