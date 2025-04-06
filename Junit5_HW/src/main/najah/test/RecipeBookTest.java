package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import main.najah.code.Recipe;
import main.najah.code.RecipeBook;
import main.najah.code.RecipeException;

@DisplayName("RecipeBook Test Suite")
public class RecipeBookTest {

    RecipeBook book;
    Recipe recipe;

    @BeforeEach
    void setUp() throws Exception {
        book = new RecipeBook();
        recipe = new Recipe();
        recipe.setName("Latte");
        recipe.setPrice("50");
        recipe.setAmtCoffee("2");
        recipe.setAmtMilk("1");
        recipe.setAmtSugar("1");
        recipe.setAmtChocolate("0");
        System.out.println("===> Initialized Recipe and RecipeBook");
    }

    @Test
    @DisplayName("Test Add Recipe Successfully")
    void testAddRecipe() {
        assertTrue(book.addRecipe(recipe));
    }

    @Test
    @DisplayName("Test Add Duplicate Recipe Fails")
    void testDuplicateRecipe() {
        book.addRecipe(recipe);
        assertFalse(book.addRecipe(recipe));
    }

    @Test
    @DisplayName("Test Delete Recipe")
    void testDeleteRecipe() {
        book.addRecipe(recipe);
        assertEquals("Latte", book.deleteRecipe(0));
    }

    @Test
    @DisplayName("Test Edit Recipe")
    void testEditRecipe() {
        book.addRecipe(recipe);
        Recipe newRecipe = new Recipe();
        newRecipe.setName("Cappuccino");
        try {
			newRecipe.setPrice("60");
		} catch (RecipeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			newRecipe.setAmtCoffee("3");
		} catch (RecipeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			newRecipe.setAmtMilk("2");
		} catch (RecipeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			newRecipe.setAmtSugar("1");
		} catch (RecipeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			newRecipe.setAmtChocolate("0");
		} catch (RecipeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        assertEquals("Latte", book.editRecipe(0, newRecipe));
    }

    @Test
    @Timeout(1)
    @DisplayName("Timeout Test for Edit Operation")
    void testTimeoutEditRecipe() {
        book.addRecipe(recipe);
        book.editRecipe(0, recipe);
    }

    @Test
    @Disabled("Waiting for persistence layer")
    @DisplayName("Disabled Test for Saving Recipes")
    void testDisabledPersistence() {
        fail("Persistence not implemented");
    }
}
