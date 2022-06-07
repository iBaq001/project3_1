package com.amr.project.webapp.config;//package com.amr.project.webapp.config;


import com.amr.project.converter.ShopToShopDtoConverter;
import com.amr.project.dao.impl.InitDBDao;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


@Component
public class StartDataBase {

    protected InitDBDao initDBDao;

    @Autowired
    public StartDataBase(InitDBDao initDBDao) {
        this.initDBDao = initDBDao;
    }

    @PostConstruct
    public void initDB() {

        Shop shop1 = new Shop("Мвидео", "mvideo@mvideo.ru", "+74957777775", 3.8,
                "«М.Видео» — российская торговая сеть по продаже бытовой техники и электроники. " +
                        "Является публичной компанией, контролируется структурами предпринимателя Саида Гуцериева.");
        Shop shop2 = new Shop("Ситилинк", "citylink@city.ru", "+74951111111", 4.2,
                "«Ситили́нк» — российская сеть магазинов, осуществляющая продажу компьютерной, цифровой и " +
                        "бытовой техники и позиционирующая себя как электронный дискаунтер. Полное наименование — ООО «Ситилинк».");
        Shop shop3 = new Shop("Техношок", "tehnoshok@tehno.ru", "+74955555555", 4.8,
                "Техношок является одной из крупных сетей магазинов в городе Санкт-Петербург, которая продает " +
                        "электронику и бытовую технику, различные аксессуары. ");


        File file = new File("src/main/resources/pictures/mvideo_logo.svg");
        Image logo = new Image(file);
        logo.setIsMain(true);



        shop1.setLogo(logo);
        initDBDao.persist(shop1);
        initDBDao.persist(shop2);
        initDBDao.persist(shop3);


        ShopDto shopDto = ShopToShopDtoConverter.convertShopToShopDto(shop2);
        shopDto.getLogo();
//        System.out.println("лого магазина: "+ ShopToShopDtoConverter.convertShopToShopDto(shop2).getLogo().getImageInBase64());

        Review review1 = new Review("отличный магазин", 5, shop1);
        Review review2 = new Review("мне все понравилось", 5, shop1);
        Review review3 = new Review("быстрая доставка", 4, shop1);
        Review review4 = new Review("обожаю", 5, shop1);
        Review review5 = new Review("нахамили, закройте их", 2, shop2);
        Review review6 = new Review("не дали чек, пожаловалась в налоговую", 3, shop2);
        Review review7 = new Review("очень вежливый персонал", 5, shop2);
        Review review8 = new Review("все отлично", 5, shop2);
        Review review9 = new Review("лучший магазин", 4, shop2);
        Review review10 = new Review("норм", 3, shop3);
        Review review11 = new Review("очень хорошо", 4, shop3);
        Review review12 = new Review("поставил бы ноль, но не могу", 1, shop3);
        Review review13 = new Review("так себе", 2, shop3);

        initDBDao.persist(review1);
        initDBDao.persist(review2);
        initDBDao.persist(review3);
        initDBDao.persist(review4);
        initDBDao.persist(review5);
        initDBDao.persist(review6);
        initDBDao.persist(review7);
        initDBDao.persist(review8);
        initDBDao.persist(review9);
        initDBDao.persist(review10);
        initDBDao.persist(review11);
        initDBDao.persist(review12);
        initDBDao.persist(review13);

        Category category1 = new Category("Электроника");
        Category category2 = new Category("Одежда и обувь");
        Category category3 = new Category("Дом и сад");
        Category category4 = new Category("Детские товары");
        Category category5 = new Category("Красота и здоровье");
        Category category6 = new Category("Спорт и отдых");
        Category category7 = new Category("Продукты питания");
        Category category8 = new Category("Аптека");

        initDBDao.persist(category1);
        initDBDao.persist(category2);
        initDBDao.persist(category3);
        initDBDao.persist(category4);
        initDBDao.persist(category5);
        initDBDao.persist(category6);
        initDBDao.persist(category7);
        initDBDao.persist(category8);

        Item item1 = new Item("Смартфон Xiaomi Redmi Note 10S 6/64GB, белый", new BigDecimal("17990.00"), 5, 2.9, "Сверхширокоугольная камера с углом обзора 118° позволяет полностью запечатлеть весь сюжет в одном кадре", category1, shop1);
        File file1 = new File("src/main/resources/pictures/Xiaomi_redmi.jpg");
        Image image1 = new Image(file1);
        image1.setIsMain(true);
        List<Image> images1 = new ArrayList<>();
        images1.add(image1);
        item1.setImages(images1);
        Item item2 = new Item("Горный, Складной Велосипед RUSH HOUR START 145, 24, 2022", new BigDecimal("17270.00"), 7, 3.8, "отличный велосипед за свою цену", category6, shop1);
        File file2 = new File("src/main/resources/pictures/velosiped.jpg");
        Image image2 = new Image(file2);
        image2.setIsMain(true);
        List<Image> images2 = new ArrayList<>();
        images2.add(image2);
        item2.setImages(images2);
        Item item3 = new Item("Сковорода Fissman GRACE, 28 см", new BigDecimal("2464.00"), 2, 4.5, "Глубокая сковорода GRACE 28x7,5см (алюминий). Приготовление пищи может быть не просто повседневной обязанностью, но и вполне творческим процессом.", category3, shop1);
        File file3 = new File("src/main/resources/pictures/skovoroda.jpg");
        Image image3 = new Image(file3);
        image3.setIsMain(true);
        List<Image> images3 = new ArrayList<>();
        images3.add(image3);
        item3.setImages(images3);
        Item item4 = new Item("Gillette Fusion ProGlide Мужская Бритва", new BigDecimal("753.00"), 12, 5.0, "Мужская бритва Gillette Fusion5 ProGlide с технологией FlexBall повторяет контуры лица, обеспечивая бескомпромиссную гладкость и комфорт.", category5, shop1);
        File file4 = new File("src/main/resources/pictures/britva.jpg");
        Image image4 = new Image(file4);
        image4.setIsMain(true);
        List<Image> images4 = new ArrayList<>();
        images4.add(image4);
        item4.setImages(images4);
        Item item5 = new Item("Силиконовые шнурки для обуви , белые", new BigDecimal("206.00"), 34, 1.7, "Силиконовые шнурки для обуви - это настоящий восторг не только для вас, но и для ваших детей и близких.", category2, shop1);
        File file5 = new File("src/main/resources/pictures/shnurki.jpg");
        Image image5 = new Image(file5);
        image5.setIsMain(true);
        List<Image> images5 = new ArrayList<>();
        images5.add(image5);
        item5.setImages(images5);
        Item item6 = new Item("Конструктор LEGO Classic 10696 Набор для творчества среднего размера", new BigDecimal("1757.00"), 3, 4.8, "Конструктор LEGO Classic 10696 «Набор для творчества среднего размера» поможет построить собственный удивительный мир.", category4, shop1);
        Item item7 = new Item("Набор инструментов для чистки ушей, 7 предметов", new BigDecimal("305.00"), 5, 3.7, "Уход за лицом, ушами и полости рта это одни из самых важных рекомендаций специалистов", category8, shop2);
        Item item8 = new Item("Вкусная жареная соленая фисташка Премиум качества 1 кг Иран", new BigDecimal("1335.00"), 13, 5, "Крупные фисташки премиум качества от компании Снекмания.", category7, shop2);
        Item item9 = new Item("Краска белая для спортивной обуви и подошв", new BigDecimal("249.00"), 71, 4.8, "alton белая краска для обуви и подошвы – это быстрый и простой способ замаскировать потертости, неровности и царапины", category2, shop2);
        Item item10 = new Item("Туалетное мыло CAMAY BOTANICALS, Японская сакура, 6 шт х 85 г", new BigDecimal("377.00"), 12, 4.9, "Хорошее мыло, достойное качество.", category5, shop2);
        Item item11 = new Item("Коптильня для горячего копчения двухъярусная эконом 50х25х20см Петромаш", new BigDecimal("1490.00"), 3, 4.4, "Коптильня российского производства Петромаш для горячего копчения рыбы, мяса, птицы, овощей в походных условиях.", category3, shop2);
        Item item12 = new Item("Портативная колонка / беспроводная колонка Defender Enjoy S900 10Вт синий", new BigDecimal("1190.00"), 23, 2.4, "Портативная колонка Defender Enjoy S900 10Вт синий, BT/FM/TF/USB/AUX:", category1, shop3);
        Item item13 = new Item("Скакалка с тонкой ручкой EasyFit черный материал ПВХ длина 3 метра", new BigDecimal("250.00"), 1, 5, "Скакалки используются для разминки, а также для повышения эффективности тренировок между упражнениями, включая аэробную составляющую", category6, shop3);
        Item item14 = new Item("Оливки микс гигантские Ozon fresh, с косточкой, Италия, 290 г", new BigDecimal("445.00"), 31, 4.9, "В нашем гастрономическом ассорти оливок Ozon fresh собраны сорта Белла Ди Чериньола (гигант), Сицилиана, Калабрезе, Каламата, Перанцана, Леччино. ", category7, shop3);
        Item item15 = new Item("NEURO BOOST Рододендрон Адамса - эффективный природный нейростимулятор", new BigDecimal("1010.00"), 9, 1.9, "Используется спецподразделениями для повышения выносливости организма и стимуляции мозговой деятельности в ходе специальных операций.", category8, shop3);
        Item item16 = new Item("Антистресс игрушка Pop it Fidge", new BigDecimal("79.00"), 77, 4.1, "op It игрушка антистресс для взрослых, подростков и детей.Pop it весёлая игра, новый хит в мире.Просто успокаивает нажатие пупырешек.", category4, shop3);
        Item item17 = new Item("Комплект носков UP, 5 пар", new BigDecimal("279.00"), 6, 4.0, "Комплект носков 5 шт, носки женские, носки короткие", category2, shop3);
        Item item18 = new Item("Sports Direction / Рукав спортивный / нарукавники компрессионные", new BigDecimal("299.00"), 5, 3.1, "Спортивные нарукавники закрепляются на бицепс, легко снимаются и одеваются. Имеют удобную подгонку, что удерживает нарукавник на месте.", category6, shop3);

        initDBDao.persist(item1);
        initDBDao.persist(item2);
        initDBDao.persist(item3);
        initDBDao.persist(item4);
        initDBDao.persist(item5);
        initDBDao.persist(item6);
        initDBDao.persist(item7);
        initDBDao.persist(item8);
        initDBDao.persist(item9);
        initDBDao.persist(item10);
        initDBDao.persist(item11);
        initDBDao.persist(item12);
        initDBDao.persist(item13);
        initDBDao.persist(item14);
        initDBDao.persist(item15);
        initDBDao.persist(item16);
        initDBDao.persist(item17);
        initDBDao.persist(item18);

        Order order1 = new Order(new GregorianCalendar(2017, Calendar.JANUARY, 25), List.of(item1, item6));
        Order order2 = new Order(new GregorianCalendar(2022, Calendar.FEBRUARY, 3), List.of(item8, item15, item3));
        Order order3 = new Order(new GregorianCalendar(2018, Calendar.MAY, 17), List.of(item4, item8));
        Order order4 = new Order(new GregorianCalendar(2018, Calendar.JUNE, 14), List.of(item13, item14, item16));
        Order order5 = new Order(new GregorianCalendar(2019, Calendar.JANUARY, 1), List.of(item17, item18));
        Order order6 = new Order(new GregorianCalendar(2019, Calendar.DECEMBER, 21), List.of(item3, item2, item6));
        Order order7 = new Order(new GregorianCalendar(2020, Calendar.SEPTEMBER, 7), List.of(item11));
        Order order8 = new Order(new GregorianCalendar(2021, Calendar.APRIL, 11), List.of(item9, item2));
        Order order9 = new Order(new GregorianCalendar(2021, Calendar.MARCH, 5), List.of(item7, item8, item9));

        initDBDao.persist(order1);
        initDBDao.persist(order2);
        initDBDao.persist(order3);
        initDBDao.persist(order4);
        initDBDao.persist(order5);
        initDBDao.persist(order6);
        initDBDao.persist(order7);
        initDBDao.persist(order8);
        initDBDao.persist(order9);


    }


}
