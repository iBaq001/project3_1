package testproject;

import com.amr.project.mapper.ShopMapper;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Shop;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

public class ShopMapperTest {

    private final ShopMapper shopMapper = Mappers.getMapper(ShopMapper.class);

    @Test
    void shopToShopDto() {

        Shop shop = new Shop();

        shop.setId(1L);
        shop.setName("Пятерочка");
        shop.setDescription("лучший магазин");
        shop.setEmail("5@mail.ru");
        shop.setPhone("5555555");
        shop.setRating(5.5);

        ShopDto shopDto = shopMapper.shopToShopDto(shop);

        assertEquals(shop.getId(), shopDto.getId());
        assertEquals(shop.getName(), shopDto.getName());
        assertEquals(shop.getDescription(), shopDto.getDescription());
        assertEquals(shop.getEmail(), shopDto.getEmail());
        assertEquals(shop.getPhone(), shopDto.getPhone());
        assertEquals(shop.getRating(), shopDto.getRating());
    }

    @Test
    void shopDtoToShop() {

        ShopDto shopDto = new ShopDto();

        shopDto.setId(2L);
        shopDto.setName("Семерочка");
        shopDto.setDescription("самый лучший магазин");
        shopDto.setEmail("7@mail.ru");
        shopDto.setPhone("7777777");
        shopDto.setRating(7.7);

        Shop shop = shopMapper.createShopDtoToShop(shopDto);

        assertEquals(shop.getId(), shopDto.getId());
        assertEquals(shop.getName(), shopDto.getName());
        assertEquals(shop.getDescription(), shopDto.getDescription());
        assertEquals(shop.getEmail(), shopDto.getEmail());
        assertEquals(shop.getPhone(), shopDto.getPhone());
        assertEquals(shop.getRating(), shopDto.getRating());
    }

}
