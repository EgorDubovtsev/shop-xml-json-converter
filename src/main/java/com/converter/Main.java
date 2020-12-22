package com.converter;

import com.converter.category.AirplaneCategory;
import com.converter.category.CarCategory;
import com.converter.category.Category;
import com.converter.entity.Product;
import com.converter.entity.SimpleProduct;
import com.converter.shop.Shop;
import com.converter.shop.SimpleShop;
import com.converter.subcategory.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
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

        Subcategory civilSubcategory = new CivilSubcategory("civil_airplane");
        civilSubcategory.addProduct(airplane2);

        Subcategory militarySubcategory = new MilitarySubcategory("military_airplane");
        militarySubcategory.addProduct(airplane1);

        Subcategory racingSubcategory = new RacingSubcategory("racing_car");
        racingSubcategory.addProduct(car1);

        Subcategory truckSubcategory = new TruckSubcategory("truck_car");
        truckSubcategory.addProduct(car2);

        Category airplaneCategory = new AirplaneCategory("airplane");
        airplaneCategory.addSubcategory(militarySubcategory);
        airplaneCategory.addSubcategory(civilSubcategory);

        Category carCategory = new CarCategory("car");
        carCategory.addSubcategory(racingSubcategory);
        carCategory.addSubcategory(truckSubcategory);

        Shop shop = new SimpleShop();
        shop.addCategory(airplaneCategory);
        shop.addCategory(carCategory);

        StringWriter stringWriter = new StringWriter();

        JAXBContext context = JAXBContext.newInstance(SimpleShop.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        File file = new File("shop.xml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))
        ) {
            marshaller.marshal(shop, bufferedWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
