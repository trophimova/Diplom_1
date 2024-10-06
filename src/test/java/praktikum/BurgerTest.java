package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
  @Mock
  Bun bun;

  @Mock
  Ingredient ingredient1;

  @Mock
  Ingredient ingredient2;

  @Mock
  Ingredient ingredient3;

  Burger burger;

  @Before
  public void setUp() {
    burger = new Burger();
  }

  @Test
  public void shouldSetBuns() {
    burger.setBuns(bun);
    assertSame(bun, burger.bun);
  }

  @Test
  public void testAddIngredient() {
    burger.addIngredient(ingredient1);
    burger.addIngredient(ingredient2);

    assertEquals(2, burger.ingredients.size());
    assertSame(ingredient1, burger.ingredients.get(0));
    assertSame(ingredient2, burger.ingredients.get(1));
  }

  @Test
  public void testRemoveIngredient() {
    burger.addIngredient(ingredient1);
    burger.addIngredient(ingredient2);
    burger.addIngredient(ingredient3);

    burger.removeIngredient(1);

    assertEquals(2, burger.ingredients.size());
    assertSame(ingredient1, burger.ingredients.get(0));
    assertSame(ingredient3, burger.ingredients.get(1));
  }

  @Test
  public void testMoveIngredient() {
    burger.addIngredient(ingredient1);
    burger.addIngredient(ingredient2);
    burger.addIngredient(ingredient3);

    burger.moveIngredient(2, 0);

    assertSame(ingredient3, burger.ingredients.get(0));
    assertSame(ingredient1, burger.ingredients.get(1));
    assertSame(ingredient2, burger.ingredients.get(2));
  }

  @Test(expected = NullPointerException.class)
  public void shouldGetPriceThrowsExceptionWhenBunIsNull() {
    burger.setBuns(null);
    burger.getPrice();
  }
}
