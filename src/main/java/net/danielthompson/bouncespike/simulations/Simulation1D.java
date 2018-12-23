package net.danielthompson.bouncespike.simulations;

import net.danielthompson.bouncespike.shapes.Circle;
import net.danielthompson.bouncespike.structures.Point2;
import net.danielthompson.bouncespike.structures.Vector2;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulation1D extends AbstractSimulation {

   private final int pixelColor = Color.WHITE.getRGB();
   private final double _g = 1.0;
   private final double _elasticity = .95;

   public List<Circle> Circles;
   public List<Vector2> Velocities;
   public List<Vector2> CollisionForces;

   public Simulation1D(SimulationState simulationState) {
      super(simulationState);
      Velocities = new ArrayList<>();

      Circles = new ArrayList<>();
      SimulationState.Circles = Circles;

      CollisionForces = new ArrayList<>();

      int halfWidth = SimulationState.width / 2;
      int halfHeight = SimulationState.height / 2;

      Random random = new Random();

      int numX = 2;

      int startI = 0 - (numX - 1) / 2;

      int endI = numX / 2;

      for (int i = startI; i <= endI; i++) {
         int x = halfWidth + i * 200;


         int r = random.nextInt(256);
         int g = random.nextInt(256);
         int b = random.nextInt(256);

         Color color = new Color(r, g, b);

         Circles.add(new Circle(x,  halfHeight, 100, color));
         Velocities.add(new Vector2(0, 0));
         CollisionForces.add(new Vector2(0, 0));
      }
   }

   @Override
   public void tick() {

      // calculate forces

      Frame++;

      for (int i = 0; i < Circles.size(); i++) {
         Circle element1 = Circles.get(i);
         Vector2 velocity1 = Velocities.get(i);
         Point2 centerOfMass1 = element1.location;
         for (int j = i + 1; j < Circles.size(); j++) {
            Circle element2 = Circles.get(j);
            Vector2 velocity2 = Velocities.get(j);
            Point2 centerOfMass2 = element2.location;

            double dx = centerOfMass2.x - centerOfMass1.x;

            Vector2 direction1 = new Vector2(dx, 0);
            direction1.normalize();

            double oneOverDistanceSquared = 1.0 / (dx * dx);

            velocity1.x += (direction1.x * oneOverDistanceSquared * _g);
            velocity2.x -= (direction1.x * oneOverDistanceSquared * _g);
         }
      }

      // update positions

      for (int i = 0; i < Circles.size(); i++) {
         Circle element = Circles.get(i);
         Vector2 velocity = Velocities.get(i);

         element.location.x += velocity.x;
      }

      // determine collisions

      for (int i = 0; i < Circles.size(); i++) {
         Circle element1 = Circles.get(i);
         Vector2 velocity1 = Velocities.get(i);
         Vector2 collisionForce1 = CollisionForces.get(i);
         for (int j = i + 1; j < Circles.size(); j++) {

            Circle element2 = Circles.get(j);
            Vector2 velocity2 = Velocities.get(j);
            Vector2 collisionForce2 = CollisionForces.get(j);

            if (element1.intersects(element2)) {
               Point2 intersection = element1.pointOfIntersection(element2);
               double halfDistance = Math.abs(intersection.x - element1.location.x);
               Vector2 e1direction = element1.location.minus(intersection);
               element1.location.x -= e1direction.x;
               element2.location.x += e1direction.x;
               velocity1.x = -velocity1.x * _elasticity;
               velocity2.x = -velocity2.x * _elasticity;
            }
         }

         collisionForce1.x = 0;
         collisionForce1.y = 0;
      }


   }
}
