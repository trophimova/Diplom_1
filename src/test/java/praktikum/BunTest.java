package praktikum;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BunTest {

  Bun bun;

  @Before
  public void setUp() {
    bun = new Bun("White Bun", 150);
  }

  @Test
  public void shouldGetName() {
    assertEquals("White Bun", bun.getName());
  }

  @Test
  public void shouldGetPrice() {
    assertEquals(150F, bun.getPrice(), 0.1);
  }
}
