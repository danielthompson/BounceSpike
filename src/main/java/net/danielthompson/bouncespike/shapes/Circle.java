package net.danielthompson.bouncespike.shapes;

import net.danielthompson.bouncespike.structures.Point2;
import net.danielthompson.bouncespike.structures.Vector2;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Circle extends AbstractShape {
   public double r;

   public Circle(double x, double y) {
      this(x, y, 1);
   }

   public Circle(double x, double y, Color c) {
      this(x, y, 1, c);
   }

   public Circle(double x, double y, double r) {
      this(x, y, r, 1);
   }

   public Circle(double x, double y, double r, Color c) {
      this(x, y, r, 1, c);
   }

   public Circle(double x, double y, double r, double m) {
      this(x, y, r, m, Color.WHITE);
   }

   public Circle(double x, double y, double r, double m, Color c) {
      this.location.x = x;
      this.location.y = y;
      this.r = r;
      this.mass = m;
      this.color = c;
   }

   public boolean intersects(Circle other) {
       return ((other.location.x - location.x) * (other.location.x - location.x) + (other.location.y - location.y) * (other.location.y - location.y) <= (other.r + r) * (other.r + r));
   }

   public Point2 pointOfIntersection(Circle other) {
      Vector2 direction = other.location.minus(location);
      direction.normalize();
      direction.scale(r);
      Point2 intersection = location.plus(direction);
      return intersection;
   }

   public double squaredDistance(Circle other) {
      return (other.location.x - location.x) * (other.location.x - location.x) + (other.location.y - location.y) * (other.location.y - location.y);
   }

   public double distance(Circle other) {
      return Math.sqrt((other.location.x - location.x) * (other.location.x - location.x) + (other.location.y - location.y) * (other.location.y - location.y));
   }

   public void draw(Graphics2D graphics2D, int number) {

      double rOver2 = r * .5;
      graphics2D.setPaint(color);
      graphics2D.fill(new Ellipse2D.Double(location.x - rOver2, location.y - rOver2, r, r));
      graphics2D.setPaint(Color.WHITE);
      graphics2D.draw(new Ellipse2D.Double(location.x - rOver2, location.y - rOver2, r - 1, r - 1));
      graphics2D.drawString(String.valueOf(number), (int) location.x, (int) location.y);
   }
}
