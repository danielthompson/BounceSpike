package net.danielthompson.bouncespike;

import java.util.TimerTask;

public class SimulationTimerTask extends TimerTask {

   private Simulation _simulation;

   public SimulationTimerTask(Simulation simulation) {
      _simulation = simulation;
   }

   @Override
   public void run() {
      _simulation.tick();
   }
}
