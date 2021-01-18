package service;

import com.converter.category.Category;
import com.converter.category.SimpleCategory;
import com.converter.entity.Product;
import com.converter.entity.SimpleProduct;
import com.converter.service.SimpleGeneratorXml;
import com.converter.service.SimpleJsonConverter;
import com.converter.service.SimpleReaderXml;
import com.converter.shop.SimpleShop;
import com.converter.subcategory.SimpleSubcategory;
import com.converter.subcategory.Subcategory;
import com.converter.utils.Color;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


 class SimpleJsonConverterTest {
    private static final String PATH_TEST_RESOURCES = "src/test/resources";
    private static final File CORRECT_FULL_SHOP_XML = new File(PATH_TEST_RESOURCES + "/shopWithCategories.xml");
    private static SimpleReaderXml<SimpleShop> readerXml = Mockito.mock(SimpleReaderXml.class);
    private static SimpleShop simpleShopFull = new SimpleShop();
    private final File FILE_FOR_JSON_GENERATING = new File(PATH_TEST_RESOURCES + "/fileToJsonGenerate.json");
    private final File CORRECT_FULL_SHOP_JSON_FILE = new File(PATH_TEST_RESOURCES + "/shopWithCategories.json");
    private final File XML_FOR_GENERATING = new File(PATH_TEST_RESOURCES + "/fileToXmlGenerate.xml");
    private final static File XML_FILE_STUB = new File("test");
    private final static SimpleJsonConverter<SimpleShop> simpleJsonConverter = new SimpleJsonConverter<>(new ObjectMapper(), readerXml);

    private SimpleGeneratorXml<SimpleShop> simpleGeneratorXml = new SimpleGeneratorXml<>();

    @BeforeAll
    static void init() {
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

        Mockito.when(readerXml.readXml(XML_FILE_STUB, SimpleShop.class)).thenReturn(simpleShopFull);
    }

    @Test
    @DisplayName("Конвертация xml в json")
    void thatConvertingToJsonIsCorrect() {
        try (
                BufferedReader bufferedReaderForCorrectJson = new BufferedReader(new FileReader(CORRECT_FULL_SHOP_JSON_FILE));
                BufferedReader bufferedReaderForGeneratedJson = new BufferedReader(new FileReader(FILE_FOR_JSON_GENERATING));
        ) {
            simpleJsonConverter.convertToJson(XML_FILE_STUB, FILE_FOR_JSON_GENERATING, SimpleShop.class);
            String correctJson = bufferedReaderForCorrectJson.readLine();
            String generatedJson = bufferedReaderForGeneratedJson.readLine();

            assertThat(correctJson, equalTo(generatedJson));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Конвертация json в xml")
    void thatConvertingToXmlIsCorrect() {
        try (
                BufferedReader bufferedReaderForGeneratedXml = new BufferedReader(new FileReader(XML_FOR_GENERATING));
                BufferedReader bufferedReaderForCorrectXml = new BufferedReader(new FileReader(CORRECT_FULL_SHOP_XML))
        ) {
            simpleJsonConverter.convertToXml(CORRECT_FULL_SHOP_JSON_FILE, XML_FOR_GENERATING, SimpleShop.class, simpleGeneratorXml);
            String correctXml = bufferedReaderForCorrectXml.readLine();
            String generatedXml = bufferedReaderForGeneratedXml.readLine();

            assertThat(correctXml, equalTo(generatedXml));
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
    }
}
