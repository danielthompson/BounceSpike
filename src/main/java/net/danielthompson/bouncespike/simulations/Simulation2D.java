package net.danielthompson.bouncespike.simulations;

import net.danielthompson.bouncespike.shapes.Box;
import net.danielthompson.bouncespike.shapes.Circle;
import net.danielthompson.bouncespike.structures.Point2;
import net.danielthompson.bouncespike.structures.Vector2;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulation2D extends AbstractSimulation {

   private final int pixelColor = Color.WHITE.getRGB();
   private final double G = 100.0;

   public List<Point2> Points;
   public List<Circle> Circles;
   public List<Box> Boxes;
   public List<Vector2> Velocities;
   public List<Vector2> CollisionForces;

   private long _frame = 0;

   public Simulation2D(SimulationState simulationState) {
      super(simulationState);
      Points = new ArrayList<>();
      SimulationState.Points = Points;
      Velocities = new ArrayList<>();

      Circles = new ArrayList<>();
      SimulationState.Circles = Circles;

      Boxes = new ArrayList<>();
      SimulationState.Boxes = Boxes;

      CollisionForces = new ArrayList<>();


      int halfWidth = SimulationState.width / 2;
      int halfHeight = SimulationState.height / 2;

//      for (int i = -1; i <= 1; i++) {
//         for (int j = -1; j <= 1; j++) {
//            Points.add(new Point2(halfWidth + i * 10,  halfHeight + j * 10, i * 10 + j));
//            Velocities.add(new Vector2(0, 0));
//         }
//      }

//      for (int i = -1; i <= 1; i++) {
//         for (int j = -1; j <= 1; j++) {
//            Circles.add(new Circle(halfWidth + i * 50,  halfHeight + j * 50, 20));
//            Velocities.add(new Vector2(0, 0));
//         }
//      }

      Random random = new Random();

      int numX = 5;
      int numY = 5;

      int startI = 0 - (numX - 1) / 2;
      int startJ = 0 - (numY - 1) / 2;

      int endI = numX / 2;
      int endJ = numY / 2;

      for (int i = startI; i <= endI; i++) {
         for (int j = startJ; j <= endJ; j++) {
            int x = halfWidth + i * 110;
            int y = halfHeight + j * 110;

            int r = random.nextInt(256);
            int g = random.nextInt(256);
            int b = random.nextInt(256);

            Color color = new Color(r, g, b);

            Circles.add(new Circle(x,  y, 100, color));
            Velocities.add(new Vector2(0, 0));
            CollisionForces.add(new Vector2(0, 0));
         }
      }

//      Boxes.add(new Box(240,  250, 100, 100, Color.GREEN));
//      Velocities.add(new Vector2(1, 1));
//      CollisionForces.add(new Vector2(0, 0));
//
//      Boxes.add(new Box(250,  750, 100, 100, Color.RED));
//      Velocities.add(new Vector2(1, -1));
//      CollisionForces.add(new Vector2(0, 0));
   }

   @Override
   public void tick() {

      // calculate forces

      _frame++;

      //System.out.println(_frame);

      if (_frame == 120) {
         int sdkljfh = 0;
      }

      for (int i = 0; i < Circles.size(); i++) {
         Circle element1 = Circles.get(i);
         Vector2 velocity1 = Velocities.get(i);
         Point2 centerOfMass1 = element1.location;
         for (int j = i + 1; j < Circles.size(); j++) {
            Circle element2 = Circles.get(j);
            Vector2 velocity2 = Velocities.get(j);
            Point2 centerOfMass2 = element2.location;

            double dx = centerOfMass2.x - centerOfMass1.x;
            double dy = centerOfMass2.y - centerOfMass1.y;

            Vector2 direction1 = new Vector2(dx, dy);
            direction1.normalize();

            double oneOverDistanceSquared = 1.0 / (dx * dx + dy * dy);

            velocity1.x += (direction1.x * oneOverDistanceSquared * G);
            velocity1.y += (direction1.y * oneOverDistanceSquared * G);

            velocity2.x -= (direction1.x * oneOverDistanceSquared * G);
            velocity2.y -= (direction1.y * oneOverDistanceSquared * G);

         }
      }

      // update positions

      for (int i = 0; i < Circles.size(); i++) {
         Circle element = Circles.get(i);
         Vector2 velocity = Velocities.get(i);

         element.location.x += velocity.x;
         element.location.y += velocity.y;
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

            double elasticity = .95;

            boolean xBounce = false;

            // they've bounced horizontally

            if (element1.intersects(element2)) {
               Point2 intersection = element1.pointOfIntersection(element2);
               Vector2 collisionDirection = element2.location.minus(element1.location);
               Vector2 tangentDirection = new Vector2(collisionDirection.y, -collisionDirection.x);

            }
         }

         element1.location.x += collisionForce1.x;
         element1.location.y += collisionForce1.y;

         collisionForce1.x = 0;
         collisionForce1.y = 0;
      }


   }
}
