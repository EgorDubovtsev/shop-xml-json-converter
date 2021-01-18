package service;

import com.converter.service.XmlValidator;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

class XmlValidatorTest {
    private static final String INVALID_XML_MESSAGE = "XML IS INVALID";
    private static final String VALID_XML_MESSAGE = "XML IS VALID";
    private static final String PATH_TEST_RESOURCES = "src/test/resources";
    private static final String PATH_MAIN_RESOURCES = "src/main/resources";
    private final File INCORRECT_XML_FILE = new File(PATH_TEST_RESOURCES + "/incorrectSimpleShop.xml");
    private final File CORRECT_XML_FILE = new File(PATH_TEST_RESOURCES + "/shopWithCategories.xml");
    private final File XML_SCHEMA = new File(PATH_MAIN_RESOURCES + "/schema.xsd");
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final XmlValidator xmlValidator = new XmlValidator();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Nested
    class PositiveCases {
        @Test
        @DisplayName("Определение корректного документа")
        void thatCorrectXmlFileWillBeDefine() {
            xmlValidator.checkValidation(CORRECT_XML_FILE, XML_SCHEMA);
            assertThat(outContent.toString(), containsString(VALID_XML_MESSAGE));
        }
    }

    @Nested
    class NegativeCases {
        @Test
        @DisplayName("Определение некорректного документа")
        void thatIncorrectXmlFileWillBeDefine() {
            xmlValidator.checkValidation(INCORRECT_XML_FILE, XML_SCHEMA);
            assertThat(outContent.toString(), containsString(INVALID_XML_MESSAGE));
        }
    }
}

