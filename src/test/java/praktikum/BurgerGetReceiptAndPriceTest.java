package praktikum;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.runners.Parameterized.*;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerGetReceiptAndPriceTest {

  private final float bunPrice;
  private final String bunName;
  private final float[] ingredientPrices;
  private final IngredientType[] ingredientTypes;
  private final String[] ingredientNames;
  private final float expectedPrice;

  @Rule
  public MockitoRule mockito = MockitoJUnit.rule();

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

  public BurgerGetReceiptAndPriceTest(float bunPrice, String bunName, float[] ingredientPrices, IngredientType[] ingredientTypes, String[] ingredientNames, float expectedPrice) {
    this.bunPrice = bunPrice;
    this.bunName = bunName;
    this.ingredientPrices = ingredientPrices;
    this.ingredientTypes = ingredientTypes;
    this.ingredientNames = ingredientNames;
    this.expectedPrice = expectedPrice;
  }

  @Parameters
  public static List<Object[]> data() {
    return Arrays.asList(new Object[][]{
        {100.50F, "Test Bun 1",
            new float[]{50.25F, 75.75F, 25.50F},
            new IngredientType[]{IngredientType.SAUCE, IngredientType.FILLING, IngredientType.SAUCE},
            new String[]{"Hot Sauce", "Cutlet", "Chili Sauce"},
            100.50F * 2 + 50.25F + 75.75F + 25.50F},

        {150.75F, "Test Bun 2",
            new float[]{60.30F, 40.40F, 30.20F},
            new IngredientType[]{IngredientType.FILLING, IngredientType.SAUCE, IngredientType.FILLING},
            new String[]{"Beef Patty", "BBQ Sauce", "Cheese"},
            150.75F * 2 + 60.30F + 40.40F + 30.20F},

        {200.25F, "Test Bun 3",
            new float[]{70.10F, 80.60F, 90.15F},
            new IngredientType[]{IngredientType.SAUCE, IngredientType.FILLING, IngredientType.SAUCE},
            new String[]{"Garlic Sauce", "Chicken", "Spicy Sauce"},
            200.25F * 2 + 70.10F + 80.60F + 90.15F}
    });
  }

  @Test
  public void testGetPrice() {
    when(bun.getPrice()).thenReturn(bunPrice);
    when(ingredient1.getPrice()).thenReturn(ingredientPrices[0]);
    when(ingredient2.getPrice()).thenReturn(ingredientPrices[1]);
    when(ingredient3.getPrice()).thenReturn(ingredientPrices[2]);

    burger.setBuns(bun);
    burger.addIngredient(ingredient1);
    burger.addIngredient(ingredient2);
    burger.addIngredient(ingredient3);

    assertEquals(expectedPrice, burger.getPrice(), 0.1);

    verify(bun, times(1)).getPrice();
    verify(ingredient1).getPrice();
    verify(ingredient2).getPrice();
    verify(ingredient3).getPrice();
  }

  @Test
  public void testGetReceipt() {
    when(bun.getName()).thenReturn(bunName);
    when(bun.getPrice()).thenReturn(bunPrice);
    when(ingredient1.getName()).thenReturn(ingredientNames[0]);
    when(ingredient1.getType()).thenReturn(ingredientTypes[0]);
    when(ingredient2.getName()).thenReturn(ingredientNames[1]);
    when(ingredient2.getType()).thenReturn(ingredientTypes[1]);
    when(ingredient3.getName()).thenReturn(ingredientNames[2]);
    when(ingredient3.getType()).thenReturn(ingredientTypes[2]);

    burger.setBuns(bun);
    burger.addIngredient(ingredient1);
    burger.addIngredient(ingredient2);
    burger.addIngredient(ingredient3);

    String expectedReceipt = String.format(
        "(==== %s ====)%n= %s %s =%n= %s %s =%n= %s %s =%n(==== %s ====)%n%nPrice: %f%n",
        bunName,
        ingredientTypes[0].toString().toLowerCase(), ingredientNames[0],
        ingredientTypes[1].toString().toLowerCase(), ingredientNames[1],
        ingredientTypes[2].toString().toLowerCase(), ingredientNames[2],
        bunName, burger.getPrice());

    assertEquals(expectedReceipt, burger.getReceipt());

    verify(bun, times(2)).getName();
    verify(ingredient1).getName();
    verify(ingredient2).getName();
    verify(ingredient3).getName();
    verify(ingredient1).getType();
    verify(ingredient2).getType();
    verify(ingredient3).getType();
  }
}
