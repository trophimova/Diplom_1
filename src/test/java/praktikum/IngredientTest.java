package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;
import static org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class  IngredientTest {

  private final IngredientType type;
  private final String name;
  private final float price;

  Ingredient ingredient;

  public IngredientTest(IngredientType type, String name, float price) {
    this.type = type;
    this.name = name;
    this.price = price;
  }

  @Before
  public void setUp() {
    ingredient = new Ingredient(type, name, price);
  }

  @Parameters
  public static Object[][] data() {
    return new Object[][] {
        { IngredientType.SAUCE, "Hot Sauce", 100F },
        { IngredientType.FILLING, "Cutlet", 200F },
        { IngredientType.SAUCE, "Chili Sauce", 300F }
    };
  }

  @Test
  public void shouldGetType() {
    assertEquals(type, ingredient.getType());
  }

  @Test
  public void shouldGetName() {
    assertEquals(name, ingredient.getName());
  }

  @Test
  public void shouldGetPrice() {
    assertEquals(price, ingredient.getPrice(), 0.1);
  }
}
