package net.danielthompson.bouncespike.simulations;

import net.danielthompson.bouncespike.shapes.Box;
import net.danielthompson.bouncespike.shapes.Circle;
import net.danielthompson.bouncespike.structures.Point2;

import java.util.List;

public class SimulationState {

   public final int width, height;
   public List<Point2> Points;
   public List<Circle> Circles;
   public List<Box> Boxes;

   public SimulationState(int width, int height) {
      this.width = width;
      this.height = height;
   }
}
