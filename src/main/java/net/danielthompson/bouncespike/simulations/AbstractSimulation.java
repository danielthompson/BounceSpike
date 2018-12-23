package net.danielthompson.bouncespike.simulations;

public abstract class AbstractSimulation {
   public SimulationState SimulationState;
   public int Frame = 0;

   public AbstractSimulation(SimulationState simulationState) {
      SimulationState = simulationState;
   }

   public abstract void tick();
}
