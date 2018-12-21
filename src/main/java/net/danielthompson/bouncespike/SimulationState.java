package net.danielthompson.bouncespike;

import java.util.List;

public class SimulationState {

   public final int width, height;
   public List<Point> Points;
   public List<Circle> Circles;
   public List<Box> Boxes;

   public SimulationState(int width, int height) {
      this.width = width;
      this.height = height;
   }
}
