package service;

import com.converter.category.Category;
import com.converter.category.SimpleCategory;
import com.converter.entity.Product;
import com.converter.entity.SimpleProduct;
import com.converter.service.SimpleReaderXml;
import com.converter.shop.SimpleShop;
import com.converter.subcategory.SimpleSubcategory;
import com.converter.subcategory.Subcategory;
import com.converter.utils.Color;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.time.Month;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNull;

class SimpleReaderXmlTest {
    private static final String PATH_TEST_RESOURCES = "src/test/resources";
    private static final File FULL_SHOP_XML = new File(PATH_TEST_RESOURCES + "/shopWithCategories.xml");
    private final File INCORRECT_SHOP_XML = new File(PATH_TEST_RESOURCES + "/incorrectSimpleShop.xml");
    private static SimpleShop simpleShopFull = new SimpleShop();
    private SimpleReaderXml<SimpleShop> simpleReaderXml = new SimpleReaderXml<>();

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

    @Nested
    class PositiveCases {
        @Test
        @DisplayName("Сериализация корректного xml файла")
        void thatObjectWillBeReadCorrectly() {
            SimpleShop takenSimpleShop = simpleReaderXml.readXml(FULL_SHOP_XML, SimpleShop.class);

            assertThat(takenSimpleShop, equalTo(simpleShopFull));
        }
    }

    @Nested
    class NegativeCases {

        @Test
        @DisplayName("Сериализация некорректного xml файла")
        void thatReadIncorrectFileWillReturnNull() {

            assertNull(simpleReaderXml.readXml(INCORRECT_SHOP_XML, SimpleShop.class));
        }

    }
}
