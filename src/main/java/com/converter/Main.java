package com.converter;

import com.converter.category.Category;
import com.converter.category.SimpleCategory;
import com.converter.entity.Product;
import com.converter.entity.SimpleProduct;
import com.converter.service.*;
import com.converter.shop.Shop;
import com.converter.shop.SimpleShop;
import com.converter.subcategory.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.bind.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;

public class Main {
    public static void main(String[] args) throws JAXBException {
        Product car1 = new SimpleProduct();
        car1.setMaker("BMW");
        car1.setModel("aa111");
        car1.setCreationDate(LocalDate.of(2006, Month.APRIL, 21));
        car1.setPrice(2000.5);
        car1.setInStock(true);

        Product car2 = new SimpleProduct();
        car2.setMaker("MERCEDES");
        car2.setModel("ab212");
        car2.setCreationDate(LocalDate.of(2002, Month.JANUARY, 14));
        car2.setPrice(4000);
        car2.setInStock(false);

        Product airplane1 = new SimpleProduct();
        airplane1.setMaker("GOVERNMENT");
        airplane1.setModel("tt171");
        airplane1.setCreationDate(LocalDate.of(2006, Month.APRIL, 21));
        airplane1.setPrice(2000.5);
        airplane1.setInStock(true);

        Product airplane2 = new SimpleProduct();
        airplane2.setMaker("YOUNG COMPANY");
        airplane2.setModel("jf555");
        airplane2.setCreationDate(LocalDate.of(2006, Month.APRIL, 21));
        airplane2.setPrice(2000.5);
        airplane2.setInStock(false);

        Subcategory civilSubcategory = new SimpleSubcategory("civil airplane");
        civilSubcategory.addProduct(airplane2);

        Subcategory militarySubcategory = new SimpleSubcategory("military airplane");
        militarySubcategory.addProduct(airplane1);

        Subcategory racingSubcategory = new SimpleSubcategory("racing car");
        racingSubcategory.addProduct(car1);

        Subcategory truckSubcategory = new SimpleSubcategory("truck car");
        truckSubcategory.addProduct(car2);

        Category airplaneCategory = new SimpleCategory("airplane");
        airplaneCategory.addSubcategory(militarySubcategory);
        airplaneCategory.addSubcategory(civilSubcategory);

        Category carCategory = new SimpleCategory("car");
        carCategory.addSubcategory(racingSubcategory);
        carCategory.addSubcategory(truckSubcategory);

        Shop shop = new SimpleShop();
        shop.addCategory(airplaneCategory);
        shop.addCategory(carCategory);

        File xmlFile = new File("shop.xml");

        GeneratorXml generatorXml = new SimpleGeneratorXml();
        generatorXml.generateXmlStAX(shop, xmlFile);

        ReaderXml<SimpleShop> readerXml = new SimpleReaderXml<>();
        System.out.println(readerXml.readXml(xmlFile, SimpleShop.class));

        ObjectMapper objectMapper = new ObjectMapper();
//        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//        objectMapper.setDateFormat(dateFormat);

        JsonConverter<SimpleShop> simpleShopJsonConverter = new SimpleJsonConverter<>(objectMapper, readerXml);
        File jsonFile = new File("shop.json");
        try {
            simpleShopJsonConverter.convertToJson(xmlFile, jsonFile, SimpleShop.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        File convertedShop = new File("convertedShop.xml");
        try {
            simpleShopJsonConverter.convertToXml(jsonFile,convertedShop,SimpleShop.class,generatorXml);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
