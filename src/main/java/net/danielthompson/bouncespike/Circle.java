package net.danielthompson.bouncespike;

public class Circle {
   public double x, y, r, m;

   public Circle(double x, double y) {
      this(x, y, 1);
   }

   public Circle(double x, double y, double r) {
      this(x, y, r, 1);
   }

   public Circle(double x, double y, double r, double m) {
      this.x = x;
      this.y = y;
      this.r = r;
      this.m = m;
   }
}
