package net.danielthompson.bouncespike.shapes;

import net.danielthompson.bouncespike.structures.Point2;

import java.awt.*;

public abstract class AbstractShape {
   public Point2 location = new Point2(0, 0);
   public Color color = Color.WHITE;
   public double mass = 1.0;
}
