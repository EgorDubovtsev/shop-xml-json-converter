package service;

import com.converter.category.Category;
import com.converter.category.SimpleCategory;
import com.converter.entity.Product;
import com.converter.entity.SimpleProduct;
import com.converter.service.SimpleGeneratorXml;
import com.converter.shop.SimpleShop;
import com.converter.subcategory.SimpleSubcategory;
import com.converter.subcategory.Subcategory;
import com.converter.utils.Color;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.time.LocalDate;
import java.time.Month;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class SimpleGeneratorXmlTest {
    private static final String PATH_TEST_RESOURCES = "src/test/resources";
    private SimpleGeneratorXml<SimpleShop> simpleGeneratorXml = new SimpleGeneratorXml<>();
    private final File FILE_FOR_XML_GENERATE_TESTING = new File(PATH_TEST_RESOURCES + "/fileToXmlGenerate.xml");
    private static SimpleShop simpleEmptyShop = new SimpleShop();
    private static SimpleShop simpleShopFull = new SimpleShop();


    @BeforeAll
    static void beforeAll() {
        Product product1 = new SimpleProduct();
        product1.setMaker("TEST");
        product1.setModel("aa111");
        product1.setCreationDate(LocalDate.of(2006, Month.APRIL, 21));
        product1.setPrice(2000.5);
        product1.setInStock(true);
        product1.setColor(Color.BLACK);
        product1.setTest("TEST value");

        Product product2 = new SimpleProduct();
        product2.setMaker("TEST");
        product2.setModel("ab212");
        product2.setCreationDate(LocalDate.of(2002, Month.JANUARY, 14));
        product2.setPrice(4000);
        product2.setInStock(false);
        product2.setColor(Color.RED);
        product2.setTest("TEST value4");


        Subcategory subcategory1 = new SimpleSubcategory("TEST_1");
        subcategory1.addProduct(product1);

        Subcategory subcategory2 = new SimpleSubcategory("TEST_2");
        subcategory2.addProduct(product2);

        Subcategory subcategory3 = new SimpleSubcategory("TEST_3");
        subcategory3.addProduct(product1);

        Subcategory subcategory4 = new SimpleSubcategory("TEST_4");
        subcategory4.addProduct(product2);

        Category category1 = new SimpleCategory("TEST_CATEGORY_1");
        category1.addSubcategory(subcategory2);
        category1.addSubcategory(subcategory1);

        Category category2 = new SimpleCategory("TEST_CATEGORY_2");
        category2.addSubcategory(subcategory3);
        category2.addSubcategory(subcategory4);

        simpleShopFull.addCategory(category1);
        simpleShopFull.addCategory(category2);

    }

    @Test
    @DisplayName("Геренация файла из Shop без категорий")
    void thatXmlWillBeCorrectGeneratedWithoutCategories() throws IOException {
        File xmlShopWithoutCategories = new File("src/test/resources/shopWithoutCategories.xml");

        try (
                BufferedReader readerForGeneratedXml = new BufferedReader(new FileReader(FILE_FOR_XML_GENERATE_TESTING));
                BufferedReader readerForCorrectXml = new BufferedReader(new FileReader(xmlShopWithoutCategories));
        ) {
            simpleGeneratorXml.generateXmlJAXB(simpleEmptyShop, FILE_FOR_XML_GENERATE_TESTING, SimpleShop.class);
            String contentOfCorrectXmlFile = readerForCorrectXml.readLine();
            String contentOfGeneratedFile = readerForGeneratedXml.readLine();

            assertThat(contentOfGeneratedFile, equalTo(contentOfCorrectXmlFile));
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Генерация файла из Shop с категориями и подкатегориями")
    void thatXmlWillBeCorrectGeneratedWithCategoriesAndSubcategories() {
        File xmlShopWithCategories = new File("src/test/resources/shopWithCategories.xml");

        try (
                BufferedReader readerForGeneratedXml = new BufferedReader(new FileReader(FILE_FOR_XML_GENERATE_TESTING));
                BufferedReader readerForCorrectXml = new BufferedReader(new FileReader(xmlShopWithCategories));
        ) {
            simpleGeneratorXml.generateXmlJAXB(simpleShopFull, FILE_FOR_XML_GENERATE_TESTING, SimpleShop.class);
            String contentOfCorrectXmlFile = readerForCorrectXml.readLine();
            String contentOfGeneratedFile = readerForGeneratedXml.readLine();

            assertThat(contentOfGeneratedFile, equalTo(contentOfCorrectXmlFile));
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }

}
