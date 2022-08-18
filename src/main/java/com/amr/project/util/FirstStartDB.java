package com.amr.project.util;

import com.amr.project.converter.ImagesToByteArrayConverter;
import com.amr.project.dao.impl.FirstStartDBDao;
import com.amr.project.model.entity.*;
import com.amr.project.model.enums.Gender;
import com.amr.project.model.enums.Status;
import com.amr.project.model.enums.PersonalDataStatus;
import com.amr.project.model.enums.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Component
@RequiredArgsConstructor
public class FirstStartDB {

    private final FirstStartDBDao<Object> firstStartDBDao;

    private final ImagesToByteArrayConverter imagesToByteArrayConverter;

    @PostConstruct
    public void runAfterDBCreated() throws IOException {

        Country country1 = Country.builder()
                .name("Россия")
                .build();

        City city1 = City.builder()
                .name("Видное")
                .country(country1)
                .build();

        City city2 = City.builder()
                .name("Краснодар")
                .country(country1)
                .build();

        City city3 = City.builder()
                .name("Москва")
                .country(country1)
                .build();

        City city4 = City.builder()
                .name("Ярославль")
                .country(country1)
                .build();

        City city5 = City.builder()
                .name("Саратов")
                .country(country1)
                .build();


        Address address1 = Address.builder()
                .cityIndex("111000")
                .street("Гальяна Футова")
                .house("106")
                .city(city1)
                .build();

        Address address2 = Address.builder()
                .cityIndex("111001")
                .street("Кукушкина")
                .house("12")
                .city(city1)
                .build();

        Address address3 = Address.builder()
                .cityIndex("122001")
                .street("Фрунзе")
                .house("16")
                .city(city2)
                .build();

        Address address4 = Address.builder()
                .cityIndex("122002")
                .street("Фрунзева")
                .house("163")
                .city(city2)
                .build();

        Address address5 = Address.builder()
                .cityIndex("555555")
                .street("Красная")
                .house("153")
                .city(city3)
                .build();

        Address address6 = Address.builder()
                .cityIndex("558555")
                .street("Земский переулок")
                .house("13")
                .city(city3)
                .build();

        Address address7 = Address.builder()
                .cityIndex("552355")
                .street("Афонина")
                .house("9")
                .city(city4)
                .build();

        Address address8 = Address.builder()
                .cityIndex("112355")
                .street("Краснодарская")
                .house("32")
                .city(city5)
                .build();


        Image moderator1image1 = Image.builder()
                .picture(imagesToByteArrayConverter.getBiteArrayImage("moderator1.jpeg"))
                .mimeType(imagesToByteArrayConverter.getMimeTypeFromImage("moderator1.jpeg"))
                .isMain(true)
                .build();
        List<Image> moderator1Images = new ArrayList<>();
        moderator1Images.add(moderator1image1);

        PersonalData moderator11PersonalData = PersonalData.builder()
                .authority("ГУМВД города Москвы")
                .passport(9999999999L)
                .dateOfIssue(new Date(1375368407000L))
                .placeOfBirth("г. Москва")
                .status(PersonalDataStatus.CONFIRMED)
                .build();

        User moderator1 = User.builder()
                .isUsing2FA(true)
                .isIdentification(true)
                .activate(true)
                .email("moderator1@mail.ru")
                .username("moderator1")
                .password("moderator1")
                .activationCode("moderator1")
                .address(address5)
                .role(Roles.MODERATOR)
                .personalData(moderator11PersonalData)
                .images(moderator1Images)
                .age(27)
                .birthday(new Calendar.Builder()
                        .setDate(1995, 6, 11).build())
                .firstName("Себостьян")
                .lastName("Аккерман")
                .gender(Gender.MALE)
                .phone("+7-999-555-55-55")
                .build();

//        UserInfo moderator1Info = UserInfo.builder()
//
//                .user(moderator1)
//                .build();


        Image admin1image1 = Image.builder()
                .picture(imagesToByteArrayConverter.getBiteArrayImage("admin1.jpeg"))
                .mimeType(imagesToByteArrayConverter.getMimeTypeFromImage("admin1.jpeg"))
                .isMain(true)
                .build();
        List<Image> admin1Images = new ArrayList<>();
        admin1Images.add(admin1image1);

        PersonalData admin1PersonalData = PersonalData.builder()
                .authority("ГУМВД города Видное")
                .passport(8888888888L)
                .dateOfIssue(new Date(1375368407000L))
                .placeOfBirth("г. Видное")
                .status(PersonalDataStatus.CONFIRMED)
                .build();

        User admin1 = User.builder()
                .isUsing2FA(true)
                .isIdentification(true)
                .activate(true)
                .email("admin1@mail.ru")
                .username("admin1")
                .password("admin1")
                .activationCode("admin1")
                .address(address1)
                .role(Roles.ADMIN)
                .personalData(admin1PersonalData)
                .images(admin1Images)
                .age(25)
                .birthday(new Calendar.Builder()
                        .setDate(1997, 1, 3).build())
                .firstName("Иван")
                .lastName("Иванов")
                .gender(Gender.MALE)
                .phone("+7-999-999-99-99")
                .build();

//        UserInfo admin1Info = UserInfo.builder()
//
//                .user(admin1)
//                .build();

        Coupon coupon1 = Coupon.builder()
                .start(Calendar.getInstance())
                .end(Calendar.getInstance())
                .user(admin1)
                .build();

        List<Coupon> couponsBeerloga = new ArrayList<>();
        couponsBeerloga.add(coupon1);

        Image shop1Logo = Image.builder()
                .picture(imagesToByteArrayConverter.getBiteArrayImage("shop1.jpeg"))
                .mimeType(imagesToByteArrayConverter.getMimeTypeFromImage("shop1.jpeg"))
                .isMain(true)
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
                .coupons(couponsBeerloga)
                .address(address2)
                .user(admin1)
                .logo(shop1Logo)
                .rating(9.9)
                .build();

        Category category1 = Category.builder()
                .shopId(1L)
                .name("Beer")
                .build();

        Category category3 = Category.builder()
                .shopId(1L)
                .name("Sidr")
                .build();

        Image item1image1 = Image.builder()
                .picture(imagesToByteArrayConverter.getBiteArrayImage("item1.jpeg"))
                .mimeType(imagesToByteArrayConverter.getMimeTypeFromImage("item1.jpeg"))
                .isMain(true)
                .build();
        List<Image> item1Images = new ArrayList<>();
        item1Images.add(item1image1);

        Item item1 = Item.builder()
                .basePrice(30L)
                .count(29)
                .description("Пиво пенное, разливное, обалденное")
                .discount(15)
                .isModerateAccept(true)
                .isModerated(true)
                .isPretendedToBeDeleted(false)
                .name("Хадыженское")
                .price(100L)
                .rating(9.5)
//                .cartItem(cartItem1)
                .category(category1)
                .shop(shop1)
                .user(admin1)
                .images(item1Images)
                .build();

        Image item3image1 = Image.builder()
                .picture(imagesToByteArrayConverter.getBiteArrayImage("item3.jpeg"))
                .mimeType(imagesToByteArrayConverter.getMimeTypeFromImage("item3.jpeg"))
                .isMain(true)
                .build();
        List<Image> item3Images = new ArrayList<>();
        item3Images.add(item3image1);

        Item item3 = Item.builder()
                .basePrice(30L)
                .count(30)
                .description("Сидр, как пиво, но лучше")
                .discount(15)
                .isModerateAccept(true)
                .isModerated(true)
                .isPretendedToBeDeleted(false)
                .name("Сидрoff")
                .price(100L)
                .rating(9.5)
//                .cartItem(cartItem1)
                .category(category3)
                .shop(shop1)
                .user(admin1)
                .images(item3Images)
                .build();

        Discount discount1 = Discount.builder()
                .minOrder(1000)
                .percentage(50)
                .fixedDiscount(15)
                .shop(shop1)
                .build();

        Discount discount3 = Discount.builder()
                .minOrder(1000)
                .percentage(50)
                .fixedDiscount(15)
                .shop(shop1)
                .build();

        CartItem cartItem1 = CartItem.builder()
                .quantity(1000)
                .user(admin1)
                .shop(shop1)
                .itemInCart(item1)
                .build();

        CartItem cartItem3 = CartItem.builder()
                .quantity(1000)
                .user(admin1)
                .shop(shop1)
                .itemInCart(item3)
                .build();


        Image admin2image1 = Image.builder()
                .picture(imagesToByteArrayConverter.getBiteArrayImage("admin2.jpeg"))
                .mimeType(imagesToByteArrayConverter.getMimeTypeFromImage("admin2.jpeg"))
                .isMain(true)
                .build();
        List<Image> admin2Images = new ArrayList<>();
        admin2Images.add(admin2image1);

        PersonalData admin2PersonalData = PersonalData.builder()
                .authority("ГУМВД города Краснодара")
                .passport(7777777777L)
                .dateOfIssue(new Date(1375368407000L))
                .placeOfBirth("г. Краснодар")
                .status(PersonalDataStatus.CONFIRMED)
                .build();

        User admin2 = User.builder()
                .isUsing2FA(true)
                .isIdentification(true)
                .activate(true)
                .email("admin2@mail.ru")
                .username("admin2")
                .password("admin2")
                .activationCode("admin2")
                .address(address3)
                .role(Roles.ADMIN)
                .personalData(admin2PersonalData)
                .images(admin2Images)
                .age(25)
                .birthday(new Calendar.Builder()
                        .setDate(1997, 6, 15).build())
                .firstName("Олег")
                .lastName("Олегарх")
                .gender(Gender.MALE)
                .phone("+7-999-999-99-88")
                .build();

//        UserInfo admin2Info = UserInfo.builder()
//
//                .user(admin2)
//                .build();

        Coupon coupon2 = Coupon.builder()
                .start(Calendar.getInstance())
                .end(Calendar.getInstance())
                .user(admin2)
                .build();

        List<Coupon> couponsRibkaMoya = new ArrayList<>();
        couponsRibkaMoya.add(coupon2);

        Image shop2Logo = Image.builder()
                .picture(imagesToByteArrayConverter.getBiteArrayImage("shop2.jpeg"))
                .mimeType(imagesToByteArrayConverter.getMimeTypeFromImage("shop2.jpeg"))
                .isMain(true)
                .build();

        Shop shop2 = Shop.builder()
                .count(1)
                .description("RibkaMoya - я твой лучший поставщик рыбы")
                .email("RibkaMoya@mail.ru")
                .isModerateAccept(true)
                .isModerated(true)
                .isPretendedToBeDeleted(false)
                .name("RibkaMoya")
                .phone("+7-800-777-35-36")
                .coupons(couponsRibkaMoya)
                .address(address4)
                .user(admin2)
                .rating(8.3)
                .logo(shop2Logo)
                .build();

        Category category2 = Category.builder()
                .shopId(2L)
                .name("Fish")
                .build();

        Image item2image1 = Image.builder()
                .picture(imagesToByteArrayConverter.getBiteArrayImage("item2.jpeg"))
                .mimeType(imagesToByteArrayConverter.getMimeTypeFromImage("item2.jpeg"))
                .isMain(true)
                .build();
        List<Image> item2Images = new ArrayList<>();
        item2Images.add(item2image1);

        Item item2 = Item.builder()
                .basePrice(30L)
                .count(2)
                .description("Язь, здоровенный, обалденный, ЯЗЬ!")
                .discount(15)
                .isModerateAccept(true)
                .isModerated(true)
                .isPretendedToBeDeleted(false)
                .name("Язь Суздальский")
                .price(100L)
                .rating(7.5)
//                .cartItem(cartItem2)
                .category(category2)
                .shop(shop2)
                .user(admin2)
                .images(item2Images)
                .build();

        Discount discount2 = Discount.builder()
                .minOrder(1000)
                .percentage(30)
                .fixedDiscount(15)
                .shop(shop2)
                .build();

        Category category4 = Category.builder()
                .shopId(2L)
                .name("SeaFoods")
                .build();

        Image item4image1 = Image.builder()
                .picture(imagesToByteArrayConverter.getBiteArrayImage("item4.jpeg"))
                .mimeType(imagesToByteArrayConverter.getMimeTypeFromImage("item4.jpeg"))
                .isMain(true)
                .build();
        List<Image> item4Images = new ArrayList<>();
        item4Images.add(item4image1);

        Item item4 = Item.builder()
                .basePrice(30L)
                .count(2)
                .description("Буль-буль кальмарики")
                .discount(15)
                .isModerateAccept(true)
                .isModerated(true)
                .isPretendedToBeDeleted(false)
                .name("Кальмары сушеные")
                .price(100L)
                .rating(8.5)
//                .cartItem(cartItem4)
                .category(category4)
                .shop(shop2)
                .user(admin2)
                .images(item4Images)
                .build();

        CartItem cartItem2 = CartItem.builder()
                .quantity(2000)
                .user(admin2)
                .shop(shop2)
                .itemInCart(item2)
                .build();

        CartItem cartItem4 = CartItem.builder()
                .quantity(2000)
                .user(admin2)
                .shop(shop2)
                .itemInCart(item4)
                .build();


        List<Item> favoriteItemsUser1 = new ArrayList<>();
        favoriteItemsUser1.add(item1);
        favoriteItemsUser1.add(item2);
        favoriteItemsUser1.add(item3);

        List<Shop> favoriteShopsUser1 = new ArrayList<>();
        favoriteShopsUser1.add(shop1);
        favoriteShopsUser1.add(shop2);

        Set<Discount> discountsUser1 = new HashSet<>();
        discountsUser1.add(discount1);
        discountsUser1.add(discount2);
        discountsUser1.add(discount3);


        Image user1image1 = Image.builder()
                .picture(imagesToByteArrayConverter.getBiteArrayImage("user1.jpeg"))
                .mimeType(imagesToByteArrayConverter.getMimeTypeFromImage("user1.jpeg"))
                .isMain(true)
                .build();
        List<Image> user1Images = new ArrayList<>();
        user1Images.add(user1image1);

        PersonalData user1PersonalData = PersonalData.builder()
                .authority("ГУМВД Мос Области")
                .passport(1234567890L)
                .dateOfIssue(new Date(631205229000L))
                .placeOfBirth("г.Москва")
                .status(PersonalDataStatus.CONFIRMED)
                .build();

        User user1 = User.builder()
                .isUsing2FA(true)
                .isIdentification(true)
                .activate(true)
                .email("user1@mail.ru")
                .username("user1")
                .password("user1")
                .activationCode("user1")
                .role(Roles.USER)
                .discounts(discountsUser1)
                .personalData(user1PersonalData)
                .address(address6)
                .images(user1Images)
                .age(25)
                .birthday(new Calendar.Builder()
                        .setDate(1997, 2, 5).build())
                .firstName("Геннадий")
                .lastName("Рублёвский")
                .gender(Gender.MALE)
                .phone("+7-999-111-99-88")
                .build();

//        UserInfo user1Info = UserInfo.builder()
//
//                .user(user1)
//                .build();

        Favorite favoriteUser1 = Favorite.builder()
                .items(favoriteItemsUser1)
                .shops(favoriteShopsUser1)
                .user(user1)
                .build();


        List<Item> favoriteItemsUser2 = new ArrayList<>();
        favoriteItemsUser2.add(item1);

        List<Shop> favoriteShopsUser2 = new ArrayList<>();
        favoriteShopsUser2.add(shop1);

        Image user2image1 = Image.builder()
                .picture(imagesToByteArrayConverter.getBiteArrayImage("user2.jpeg"))
                .mimeType(imagesToByteArrayConverter.getMimeTypeFromImage("user2.jpeg"))
                .isMain(true)
                .build();
        List<Image> user2Images = new ArrayList<>();
        user2Images.add(user2image1);

        PersonalData user2PersonalData = PersonalData.builder()
                .authority("ГУМВД города Ярославль")
                .passport(1001331113L)
                .dateOfIssue(new Date(631205229000L))
                .placeOfBirth("г.Ярославль")
                .status(PersonalDataStatus.CONFIRMED)
                .build();

        User user2 = User.builder()
                .isUsing2FA(true)
                .isIdentification(true)
                .activate(true)
                .email("user2@mail.ru")
                .username("user2")
                .password("user2")
                .activationCode("user2")
                .role(Roles.USER)
                .personalData(user2PersonalData)
                .address(address7)
                .images(user2Images)
                .age(25)
                .birthday(new Calendar.Builder()
                        .setDate(1997, 4, 11).build())
                .firstName("Артемий")
                .lastName("Фокша")
                .gender(Gender.MALE)
                .phone("+7-999-441-99-88")
                .build();

//        UserInfo user2Info = UserInfo.builder()
//
//                .user(user2)
//                .build();

        Favorite favoriteUser2 = Favorite.builder()
                .items(favoriteItemsUser2)
                .shops(favoriteShopsUser2)
                .user(user2)
                .build();

        Image user3image1 = Image.builder()
                .picture(imagesToByteArrayConverter.getBiteArrayImage("user3.jpeg"))
                .mimeType(imagesToByteArrayConverter.getMimeTypeFromImage("user3.jpeg"))
                .isMain(true)
                .build();
        List<Image> user3Images = new ArrayList<>();
        user3Images.add(user3image1);

        PersonalData user3PersonalData = PersonalData.builder()
                .authority("ГУМВД города Саратов")
                .passport(1001333313L)
                .dateOfIssue(new Date(631205229000L))
                .placeOfBirth("г.Саратов")
                .status(PersonalDataStatus.CONFIRMED)
                .build();

        User user3 = User.builder()
                .isUsing2FA(false)
                .isIdentification(false)
                .activate(true)
                .email("user3@mail.ru")
                .username("user3")
                .password("user3")
                .activationCode("user3")
                .role(Roles.USER)
                .personalData(user3PersonalData)
                .address(address8)
                .images(user3Images)
                .age(25)
                .birthday(new Calendar.Builder()
                        .setDate(1997, 3, 7).build())
                .firstName("Виталий")
                .lastName("Гормаш")
                .gender(Gender.MALE)
                .phone("+7-939-441-54-88")
                .build();

//        UserInfo user3Info = UserInfo.builder()
//
//                .user(user3)
//                .build();

        Favorite favoriteModerator1 = Favorite.builder()
                .user(moderator1)
                .build();

        Favorite favoriteAdmin1 = Favorite.builder()
                .user(admin1)
                .build();

        Favorite favoriteAdmin2 = Favorite.builder()
                .user(admin2)
                .build();

        Favorite favoriteUser3 = Favorite.builder()
                .user(user3)
                .build();

        Calendar date = Calendar.getInstance();
        date.add(Calendar.WEEK_OF_MONTH, 1);
        Calendar date2 = Calendar.getInstance();
        date2.add(Calendar.WEEK_OF_MONTH, -1);
        List<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item3);
        items.add(item2);
        items.add(item2);
        items.add(item2);
        items.add(item1);

        List<Item> items2 = new ArrayList<>();
        items2.add(item1);
        items2.add(item1);
        items2.add(item1);
        items2.add(item3);
        items2.add(item2);
        items2.add(item2);
        items2.add(item2);
        items2.add(item2);
        items2.add(item2);
        items2.add(item2);
        items2.add(item1);


        Order order = Order.builder()
                .orderDate(Calendar.getInstance())
                .itemsInOrder(items)
                .total(new BigDecimal(390))
                .address(address1)
                .expectedDeliveryDate(date)
                .user(user3)
                .status(Status.START)
                .build();

        Order order2 = Order.builder()
                .orderDate(date2)
                .itemsInOrder(items2)
                .total(new BigDecimal(1))
                .address(address3)
                .expectedDeliveryDate(date)
                .user(user2)
                .status(Status.START)
                .build();


        firstStartDBDao.persist(country1);
        firstStartDBDao.persist(city1);
        firstStartDBDao.persist(city2);
        firstStartDBDao.persist(city3);
        firstStartDBDao.persist(city4);
        firstStartDBDao.persist(city5);
        firstStartDBDao.persist(address1);
        firstStartDBDao.persist(address2);
        firstStartDBDao.persist(address3);
        firstStartDBDao.persist(address4);
        firstStartDBDao.persist(address5);
        firstStartDBDao.persist(address6);
        firstStartDBDao.persist(address7);
        firstStartDBDao.persist(address8);

        firstStartDBDao.persist(moderator1);
//        firstStartDBDao.persist(moderator1Info);
        firstStartDBDao.persist(favoriteModerator1);

        firstStartDBDao.persist(admin1);
        firstStartDBDao.persist(favoriteAdmin1);
//        firstStartDBDao.persist(admin1Info);
        firstStartDBDao.persist(shop1);

        firstStartDBDao.persist(category1);
        firstStartDBDao.persist(item1);
        firstStartDBDao.persist(category3);
        firstStartDBDao.persist(item3);

        firstStartDBDao.persist(admin2);
        firstStartDBDao.persist(favoriteAdmin2);
//        firstStartDBDao.persist(admin2Info);
        firstStartDBDao.persist(shop2);

        firstStartDBDao.persist(category2);
        firstStartDBDao.persist(item2);
        firstStartDBDao.persist(category4);
        firstStartDBDao.persist(item4);

        firstStartDBDao.persist(user1);
//        firstStartDBDao.persist(user1Info);
        firstStartDBDao.persist(favoriteUser1);

        firstStartDBDao.persist(user2);
//        firstStartDBDao.persist(user2Info);
        firstStartDBDao.persist(favoriteUser2);

        firstStartDBDao.persist(user3);
//        firstStartDBDao.persist(user3Info);
        firstStartDBDao.persist(favoriteUser3);

//        firstStartDBDao.persist(cartItem1);
//        firstStartDBDao.persist(cartItem3);
//        firstStartDBDao.persist(cartItem2);
//        firstStartDBDao.persist(cartItem4);


        firstStartDBDao.persist(order);
        firstStartDBDao.persist(order2);
    }
}

