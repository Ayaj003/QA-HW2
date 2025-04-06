package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import main.najah.code.Product;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Product Test Suite")
public class ProductTest {

    Product product;

    @BeforeAll
    static void initAll() {
        System.out.println("===> Product Test Suite Starting");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("===> Product Test Suite Completed");
    }

    @BeforeEach
    void setUp() {
        product = new Product("Coffee", 100.0);
        System.out.println("===> New product created");
    }

    @AfterEach
    void cleanUp() {
        System.out.println("===> Product test done");
    }

    @Test
    @Order(1)
    @DisplayName("Test Final Price Without Discount")
    void testNoDiscountPrice() {
        assertEquals(100.0, product.getFinalPrice());
    }

    @Test
    @Order(2)
    @DisplayName("Test Apply Valid Discount")
    void testApplyDiscount() {
        product.applyDiscount(20);
        assertEquals(80.0, product.getFinalPrice());
    }

    @Test
    @Order(3)
    @DisplayName("Test Invalid Discount Over 50")
    void testDiscountOverLimit() {
        assertThrows(IllegalArgumentException.class, () -> product.applyDiscount(60));
    }

    @Test
    @Order(4)
    @DisplayName("Test Invalid Discount Negative")
    void testNegativeDiscount() {
        assertThrows(IllegalArgumentException.class, () -> product.applyDiscount(-5));
    }

    @Test
    @Order(5)
    @DisplayName("Multiple Assertions on Product Properties")
    void testProductProperties() {
        assertAll("Product properties",
            () -> assertEquals("Coffee", product.getName()),
            () -> assertEquals(100.0, product.getPrice()),
            () -> assertEquals(0.0, product.getDiscount())
        );
    }

    @ParameterizedTest
    @Order(6)
    @DisplayName("Test Discount Application with Valid Values")
    @ValueSource(doubles = {0, 10, 25, 50})
    void testParameterizedDiscount(double discount) {
        product.applyDiscount(discount);
        assertTrue(product.getFinalPrice() <= product.getPrice());
    }

    @Test
    @Order(7)
    @Timeout(1)
    @DisplayName("Timeout Test for Final Price Calculation")
    void testTimeoutFinalPrice() {
        assertEquals(100.0, product.getFinalPrice());
    }

    @Test
    @Order(8)
    @Disabled("Waiting for setPrice feature")
    @DisplayName("Disabled Test for Future Price Setter")
    void testSetPriceDisabled() {
        fail("Not implemented yet");
    }
}
