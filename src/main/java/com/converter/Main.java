package com.converter;

import com.converter.category.Categories;
import com.converter.category.SimpleCategory;
import com.converter.entity.Product;
import com.converter.entity.SimpleProduct;
import com.converter.service.*;
import com.converter.shop.SimpleShop;
import com.converter.subcategory.*;
import com.converter.utils.Color;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.bind.*;
import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) throws JAXBException {

        SimpleShop.Categories.Subcategories.Products airplane2 = new SimpleShop.Categories.Subcategories.Products();
        airplane2.setMaker("YOUNG COMPANY");
        airplane2.setModel("jf555");
        airplane2.setCreationDate(LocalDate.of(2006, Month.APRIL, 21).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        airplane2.setPrice(2000.5);
        airplane2.setIsInStock(false);
        airplane2.setColor("BLACK");
        airplane2.setTest("Attribute value2");

        SimpleShop.Categories.Subcategories civilSubcategory = new SimpleShop.Categories.Subcategories();
        civilSubcategory.setName("civil airplane");
        civilSubcategory.setProducts(airplane2);

        SimpleShop.Categories.Subcategories truckSubcategory = new SimpleShop.Categories.Subcategories();
        truckSubcategory.setName("truck car");
        truckSubcategory.setProducts(airplane2);

        SimpleShop.Categories airplaneCategory = new SimpleShop.Categories();
        airplaneCategory.setName("airplane");
        airplaneCategory.getSubcategories().add(civilSubcategory);

        SimpleShop.Categories carCategory = new SimpleShop.Categories();
        carCategory.setName("car");
        carCategory.getSubcategories().add(truckSubcategory);

        SimpleShop shop = new SimpleShop();
        shop.getCategories().add(airplaneCategory);
        shop.getCategories().add(carCategory);

        File xmlFile = new File("shop.xml");

        GeneratorXml generatorXml = new SimpleGeneratorXml();
        generatorXml.generateXmlJAXB(shop, xmlFile);

        ReaderXml<SimpleShop> readerXml = new SimpleReaderXml<>();
        System.out.println(readerXml.readXml(xmlFile, SimpleShop.class));

        ObjectMapper objectMapper = new ObjectMapper();
        JsonConverter<SimpleShop> simpleShopJsonConverter = new SimpleJsonConverter<>(objectMapper, readerXml);
        File jsonFile = new File("shop.json");
        try {
            simpleShopJsonConverter.convertToJson(xmlFile, jsonFile, SimpleShop.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        File convertedShop = new File("convertedShop.xml");
        try {
            simpleShopJsonConverter.convertToXml(jsonFile, convertedShop, SimpleShop.class, generatorXml);
        } catch (IOException e) {
            e.printStackTrace();
        }
        XmlValidator xmlValidator = new XmlValidator();
        xmlValidator.checkValidation(xmlFile, new File("src/main/resources/schema.xsd"));
    }
}
