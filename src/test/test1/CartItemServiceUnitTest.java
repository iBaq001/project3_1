package test1;


import com.amr.project.dao.abstracts.CartItemDao;
import com.amr.project.mapper.CartItemMapper;
import com.amr.project.mapper.ItemMapper;
import com.amr.project.model.entity.CartItem;
import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Shop;
import com.amr.project.model.entity.User;
import com.amr.project.model.enums.Gender;
import com.amr.project.model.enums.Roles;
import com.amr.project.service.abstracts.ItemService;
import com.amr.project.service.abstracts.ShopService;
import com.amr.project.service.abstracts.UserService;
import com.amr.project.service.impl.CartItemServiceImpl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CartItemServiceUnitTest {

    @Mock
    private CartItemDao cartItemDao;
    @Mock
    private ItemService itemService;
    private ItemMapper itemMapper = Mappers.getMapper(ItemMapper.class);
    @Mock
    private ShopService shopService;
    @Mock
    private UserService userService;
    private CartItemMapper cartItemMapper = Mappers.getMapper(CartItemMapper.class);

    @Spy
    List<CartItem> list;

    @InjectMocks
    private CartItemServiceImpl cartItemService;


    @Test
    public void setUserUnitTest() {
        // 1. Установление анонимного пользователя при отсутствии аутентификации
        cartItemService.setUser(null);
        Assert.assertEquals(cartItemService.getCurrentUser().getRole(), Roles.ANONYMOUS);
        // 2. Логин пользователя
        User admin1 = User.builder()
                .isUsing2FA(true)
                .isIdentification(true)
                .activate(true)
                .email("admin1@mail.ru")
                .username("admin1")
                .password("admin1")
                .activationCode("admin1")
                .role(Roles.ADMIN)
                .age(25)
                .birthday(new Calendar.Builder()
                        .setDate(1997, 1, 3).build())
                .firstName("Иван")
                .lastName("Иванов")
                .gender(Gender.MALE)
                .phone("+7-999-999-99-99")
                .build();
        cartItemService.setUser(admin1);
        Assert.assertEquals(cartItemService.getCurrentUser().getEmail(), "admin1@mail.ru");
    }

    @Test
    public void joinSameCartItemsTest() {

        cartItemService.setUser(null);

        User admin1 = User.builder()
                .isUsing2FA(true)
                .isIdentification(true)
                .activate(true)
                .email("admin1@mail.ru")
                .username("admin1")
                .password("admin1")
                .activationCode("admin1")
                .role(Roles.ADMIN)
                .age(25)
                .birthday(new Calendar.Builder()
                        .setDate(1997, 1, 3).build())
                .firstName("Иван")
                .lastName("Иванов")
                .gender(Gender.MALE)
                .phone("+7-999-999-99-99")
                .build();


        Shop shop1 = Shop.builder()
                .count(1)
                .description("BeerЛога - лучший магазин пенной продукции")
                .email("BeerLoga@mail.ru")
                .isModerateAccept(true)
                .isModerated(true)
                .isPretendedToBeDeleted(false)
                .name("BeerЛога")
                .phone("+7-800-555-35-35")

                .rating(9.9)
                .build();

        Item item1 = Item.builder()
                .basePrice(BigDecimal.valueOf(100L))
                .count(29)
                .description("Пиво пенное, разливное, обалденное")
                .discount(15)
                .isModerateAccept(true)
                .isModerated(true)
                .isPretendedToBeDeleted(false)
                .name("Хадыженское")
                .price(BigDecimal.valueOf(85L))
                .rating(9.5)
                .shop(shop1)
                .build();

        Item item3 = Item.builder()
                .basePrice(BigDecimal.valueOf(100L))
                .count(30)
                .description("Сидр, как пиво, но лучше")
                .discount(15)
                .isModerateAccept(true)
                .isModerated(true)
                .isPretendedToBeDeleted(false)
                .name("Сидрoff")
                .price(BigDecimal.valueOf(85L))
                .rating(9.5)
                .shop(shop1)
                .build();

        User user0 = cartItemService.getCurrentUser();

        list = new ArrayList<>();
        list.add(new CartItem(1L, 10, user0, shop1, item1));
        list.add(new CartItem(1L, 20, user0, shop1, item3));
        list.add(new CartItem(1L, 1, user0, shop1, item1));
        list.add(new CartItem(1L, 2, user0, shop1, item3));
        Mockito.when(cartItemService.getListOfCartItemsTest(user0)).thenReturn(list);
        cartItemService.joinSameCartItemsTest(user0);


        Assert.assertEquals(11, list.get(0).getQuantity());
        Assert.assertEquals(22, list.get(1).getQuantity());
        Assert.assertEquals(2, list.size());

    }

}
