package net.danielthompson.bouncespike.shapes;

import net.danielthompson.bouncespike.structures.Point2;

import java.awt.*;

public class Box {
   public double x, y, w, h;
   public Color color;

   public Box(double x, double y, double w, double h, Color color) {
      this.x = x;
      this.y = y;
      this.w = w;
      this.h = h;
      this.color = color;
   }

   public Point2 getCenterOfMass() {
      return new Point2(x + .5 * w, y + .5 * h);
   }

   public boolean intersects(Box other) {

      double x0 = x;
      double x1 = x + w;

      double y0 = y;
      double y1 = y + h;

      double x2 = other.x;
      double x3 = other.x + other.w;

      double y2 = other.y;
      double y3 = other.y + other.h;

      if (x1 < x2 || x3 < x0)
         return false;

      return !(y1 < y2) && !(y3 < y0);
   }
}
