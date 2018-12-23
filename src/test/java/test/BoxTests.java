package test;

import net.danielthompson.bouncespike.shapes.Box;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;

public class BoxTests {

   @Test
   public void shouldIntersect1() {
      Box b0 = new Box(0, 0, 10, 10, Color.WHITE);
      Box b1 = new Box(1, 1, 11, 11, Color.WHITE);

      boolean actual1 = b0.intersects(b1);
      boolean actual2 = b1.intersects(b0);

      Assert.assertTrue(actual1);
      Assert.assertTrue(actual2);
   }

   @Test
   public void shouldIntersect2() {
      Box b0 = new Box(0, 0, 11, 11, Color.WHITE);
      Box b1 = new Box(0, 0, 10, 10, Color.WHITE);

      boolean actual1 = b0.intersects(b1);
      boolean actual2 = b1.intersects(b0);

      Assert.assertTrue(actual1);
      Assert.assertTrue(actual2);
   }

   @Test
   public void shouldIntersect3() {
      Box b0 = new Box(10, 10, 11, 11, Color.WHITE);
      Box b1 = new Box(0, 0, 10, 10, Color.WHITE);

      boolean actual1 = b0.intersects(b1);
      boolean actual2 = b1.intersects(b0);

      Assert.assertTrue(actual1);
      Assert.assertTrue(actual2);
   }

   @Test
   public void shouldIntersect4() {
      Box b0 = new Box(0, 0, 10, 10, Color.WHITE);
      Box b1 = new Box(10, 0, 10, 10, Color.WHITE);

      boolean actual1 = b0.intersects(b1);
      boolean actual2 = b1.intersects(b0);

      Assert.assertTrue(actual1);
      Assert.assertTrue(actual2);
   }

   @Test
   public void shouldIntersect5() {
      Box b0 = new Box(0, 0, 10, 10, Color.WHITE);
      Box b1 = new Box(-5, 5, 10, 10, Color.WHITE);

      boolean actual1 = b0.intersects(b1);
      boolean actual2 = b1.intersects(b0);

      Assert.assertTrue(actual1);
      Assert.assertTrue(actual2);
   }

   @Test
   public void shouldNotIntersect1() {
      Box b0 = new Box(0, 0, 10, 10, Color.WHITE);
      Box b1 = new Box(11, 11, 10, 10, Color.WHITE);

      boolean actual1 = b0.intersects(b1);
      boolean actual2 = b1.intersects(b0);

      Assert.assertFalse(actual1);
      Assert.assertFalse(actual2);
   }
}
