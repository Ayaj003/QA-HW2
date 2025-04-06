package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import main.najah.code.Calculator;

@TestMethodOrder(OrderAnnotation.class)
@DisplayName("JUnit 5 Homework - Calculator Test")
@Execution(ExecutionMode.CONCURRENT)
public class CalculatorTest {

    Calculator calc;

    @BeforeAll
    static void initAll() {
        System.out.println("===> Starting Calculator Tests");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("===> All Calculator Tests Completed");
    }

    @BeforeEach
    void setUp() {
        calc = new Calculator();
        System.out.println("===> Setting up Calculator");
    }

    @AfterEach
    void tearDown() {
        System.out.println("===> Test completed");
    }

    @Test
    @Order(1)
    @DisplayName("Test Add Method with Multiple Values")
    void testAddMultipleValues() {
        assertEquals(10, calc.add(1, 2, 3, 4));
    }

    @Test
    @Order(2)
    @DisplayName("Test Divide Normal Case")
    void testDivide() {
        assertEquals(5, calc.divide(10, 2));
    }

    @Test
    @Order(3)
    @DisplayName("Test Divide by Zero Exception")
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> calc.divide(5, 0));
    }

    @Test
    @Order(4)
    @DisplayName("Test Factorial of Positive Number")
    void testFactorial() {
        assertEquals(120, calc.factorial(5));
    }

    @Test
    @Order(5)
    @DisplayName("Test Factorial of Zero")
    void testFactorialZero() {
        assertEquals(1, calc.factorial(0));
    }

    @Test
    @Order(6)
    @DisplayName("Test Factorial Negative Input Exception")
    void testFactorialNegative() {
        assertThrows(IllegalArgumentException.class, () -> calc.factorial(-3));
    }

    @ParameterizedTest
    @Order(7)
    @DisplayName("Test Add Method with @CsvSource")
    @CsvSource({
        "1, 2, 3",
        "5, 10, 15",
        "-2, 2, 0"
    })
    void testAddCsvSource(int a, int b, int expected) {
        assertEquals(expected, calc.add(a, b));
    }

    @ParameterizedTest
    @Order(8)
    @DisplayName("Test Factorial is Positive")
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void testFactorialPositiveValues(int value) {
        assertTrue(calc.factorial(value) > 0);
    }

    @Test
    @Order(9)
    @DisplayName("Multiple Assertions with assertAll")
    void testAssertAll() {
        assertAll("Calculator Grouped Assertions",
            () -> assertEquals(6, calc.add(1, 2, 3)),
            () -> assertEquals(3, calc.divide(9, 3)),
            () -> assertEquals(24, calc.factorial(4))
        );
    }

    @Test
    @Order(10)
    @Timeout(2)
    @DisplayName("Test with Timeout (2 seconds max)")
    void testWithTimeout() {
        assertEquals(3628800, calc.factorial(10)); 
    }

    @Test
    @Order(11)
    @Disabled("Disabled until subtract method is implemented")
    @DisplayName("Test Subtract Method (Disabled)")
    void testSubtractDisabled() {
        fail("Not implemented yet");
    }
}
