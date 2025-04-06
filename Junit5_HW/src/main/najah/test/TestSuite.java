package main.najah.test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

// 🔗 List your test classes here
@Suite
@SelectClasses({
    CalculatorTest.class,
    ProductTest.class,
    UserServiceTest.class,
    RecipeBookTest.class
})
public class TestSuite {
    
}
