package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;


import main.najah.code.UserService;

@Execution(ExecutionMode.CONCURRENT)
@DisplayName("User Service Test")
public class UserServiceTest {

    UserService service;

    @BeforeEach
    void setUp() {
        service = new UserService();
        System.out.println("===> UserService test setup complete");
    }

    @Test
    @DisplayName("Valid Email Check")
    void testValidEmail() {
        assertTrue(service.isValidEmail("user@example.com"));
    }

    @Test
    @DisplayName("Invalid Email Check")
    void testInvalidEmail() {
        assertFalse(service.isValidEmail("invalid-email"));
    }

    @ParameterizedTest
    @CsvSource({
        "admin,1234,true",
        "admin,wrong,false",
        "user,1234,false"
    })
    @DisplayName("Authentication Check")
    void testAuthentication(String username, String password, boolean expected) {
        assertEquals(expected, service.authenticate(username, password));
    }

    @Test
    @Timeout(1)
    @DisplayName("Timeout Test for Email Validation")
    void testTimeout() {
        assertTrue(service.isValidEmail("a@b.c"));
    }

    @Test
    @Disabled("Disabled until external service is mocked")
    @DisplayName("Disabled login test")
    void testLoginDisabled() {
        fail("Pending feature");
    }
}
