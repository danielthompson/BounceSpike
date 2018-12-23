package test;

import net.danielthompson.bouncespike.shapes.Circle;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CircleTests {

   @Test
   public void shouldIntersect1() {
      Circle c0 = new Circle(0, 0, 2);

      boolean actual = c0.intersects(c0);

      Assert.assertTrue(actual);
   }

   @Test
   public void shouldIntersect2() {
      Circle c0 = new Circle(0, 0, 2);
      Circle c1 = new Circle(1, 1, 2);

      boolean actual0 = c0.intersects(c1);
      boolean actual1 = c1.intersects(c0);

      Assert.assertTrue(actual0);
      Assert.assertTrue(actual1);
   }

   @Test
   public void shouldIntersect3() {
      Circle c0 = new Circle(0, 0, 1);
      Circle c1 = new Circle(0, 2, 1);

      boolean actual0 = c0.intersects(c1);
      boolean actual1 = c1.intersects(c0);

      Assert.assertTrue(actual0);
      Assert.assertTrue(actual1);
   }

   @Test
   public void shouldNotIntersect3() {
      Circle c0 = new Circle(0, 0, 1);
      Circle c1 = new Circle(0, 3, 1);

      boolean actual0 = c0.intersects(c1);
      boolean actual1 = c1.intersects(c0);

      Assert.assertFalse(actual0);
      Assert.assertFalse(actual1);
   }
}
