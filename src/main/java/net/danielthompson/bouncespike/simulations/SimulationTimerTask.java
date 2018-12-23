package net.danielthompson.bouncespike.simulations;

import java.util.TimerTask;

public class SimulationTimerTask extends TimerTask {

   private AbstractSimulation _simulation;

   public SimulationTimerTask(AbstractSimulation simulation) {
      _simulation = simulation;
   }

   @Override
   public void run() {
      _simulation.tick();
   }
}