package net.danielthompson.bouncespike;

public class Vector2 {
   public double x, y;

   public Vector2(double x, double y) {
      this.x = x;
      this.y = y;
   }

   public double length() {
      return Math.sqrt(x * x + y * y);
   }

   public void normalize() {
      double length = length();
      x /= length;
      y /= length;
   }

   @Override
   public String toString() {
      return x + " " + y;
   }
}
