package net.danielthompson.bouncespike;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Timer;

public class Main {

   private static final int Width = 1920;
   private static final int Height = 1080;

   public static void main(String[] args) {

      BufferedImage image = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_ARGB);

      SimulationState state = new SimulationState(Width, Height);

      Canvas canvas = new MainCanvas(image, state);

      Frame frame = new Frame("Bounce Spike");
      frame.add("Center", canvas);

      frame.setSize(Width, Height);
      frame.setVisible(true);

      Simulation simulation = new Simulation(state);

      Timer timer = new Timer();

      SimulationTimerTask simulationTimerTask = new SimulationTimerTask(simulation);
      CanvasTimerTask canvasTimerTask = new CanvasTimerTask(canvas, canvas.getGraphics());

      timer.schedule(simulationTimerTask, 1000, 20);
      timer.schedule(canvasTimerTask, 1025, 20);

   }

}
