package net.danielthompson.bouncespike.structures;

public class Point2 {
   public double x;
   public double y;

   public Point2(double x, double y) {
      this.x = x;
      this.y = y;
   }

   public Vector2 minus(Point2 other) {
      return new Vector2(x - other.x, y - other.y);
   }

   public Point2 plus(Vector2 other) {
      return new Point2(x + other.x, y + other.y);
   }

   @Override
   public String toString() {
      return x + " " + y;
   }


}
