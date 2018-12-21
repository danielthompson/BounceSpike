package net.danielthompson.bouncespike;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulation {

   private final SimulationState _simulationState;

   private final int pixelColor = Color.WHITE.getRGB();
   private final double G = 100.0;

   public List<Point> Points;
   public List<Circle> Circles;
   public List<Box> Boxes;
   public List<Vector2> Velocities;
   public List<Vector2> CollisionForces;

   private long _frame = 0;

   public Simulation(SimulationState simulationState) {
      _simulationState = simulationState;
      Points = new ArrayList<>();
      _simulationState.Points = Points;
      Velocities = new ArrayList<>();

      Circles = new ArrayList<>();
      _simulationState.Circles = Circles;

      Boxes = new ArrayList<>();
      _simulationState.Boxes = Boxes;

      CollisionForces = new ArrayList<>();


      int halfWidth = _simulationState.width / 2;
      int halfHeight = _simulationState.height / 2;

//      for (int i = -1; i <= 1; i++) {
//         for (int j = -1; j <= 1; j++) {
//            Points.add(new Point(halfWidth + i * 10,  halfHeight + j * 10, i * 10 + j));
//            Velocities.add(new Vector2(0, 0));
//         }
//      }

//      for (int i = -1; i <= 1; i++) {
//         for (int j = -1; j <= 1; j++) {
//            Circles.add(new Circle(halfWidth + i * 50,  halfHeight + j * 50, 20));
//            Velocities.add(new Vector2(0, 0));
//         }
//      }

//      Random random = new Random();
//
//      int numX = 5;
//      int numY = 5;
//
//      int startI = 0 - (numX - 1) / 2;
//      int startJ = 0 - (numY - 1) / 2;
//
//      int endI = numX / 2;
//      int endJ = numY / 2;
//
//      for (int i = startI; i <= endI; i++) {
//         for (int j = startJ; j <= endJ; j++) {
//            int x = halfWidth + i * 110;
//            int y = halfHeight + j * 110;
//
//            int r = random.nextInt(256);
//            int g = random.nextInt(256);
//            int b = random.nextInt(256);
//
//            Color color = new Color(r, g, b);
//
//            Boxes.add(new Box(x,  y, 100, 100, color));
//            Velocities.add(new Vector2(0, 0));
//            CollisionForces.add(new Vector2(0, 0));
//         }
//      }

      Boxes.add(new Box(240,  250, 100, 100, Color.GREEN));
      Velocities.add(new Vector2(1, 1));
      CollisionForces.add(new Vector2(0, 0));

      Boxes.add(new Box(250,  750, 100, 100, Color.RED));
      Velocities.add(new Vector2(1, -1));
      CollisionForces.add(new Vector2(0, 0));
   }

   public void tick() {

      // calculate forces

      _frame++;

      //System.out.println(_frame);

      if (_frame == 120) {
         int sdkljfh = 0;
      }

      for (int i = 0; i < Boxes.size(); i++) {
         Box element1 = Boxes.get(i);
         Vector2 velocity1 = Velocities.get(i);
         Point centerOfMass1 = element1.getCenterOfMass();
         for (int j = i + 1; j < Boxes.size(); j++) {
            Box element2 = Boxes.get(j);
            Vector2 velocity2 = Velocities.get(j);
            Point centerOfMass2 = element2.getCenterOfMass();

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

      for (int i = 0; i < Boxes.size(); i++) {
         Box element = Boxes.get(i);
         Vector2 velocity = Velocities.get(i);

         element.x += velocity.x;
         element.y += velocity.y;

      }

      // determine collisions

      for (int i = 0; i < Boxes.size(); i++) {
         Box element1 = Boxes.get(i);
         Vector2 velocity1 = Velocities.get(i);
         Vector2 collisionForce1 = CollisionForces.get(i);
         for (int j = i + 1; j < Boxes.size(); j++) {

            Box element2 = Boxes.get(j);
            Vector2 velocity2 = Velocities.get(j);
            Vector2 collisionForce2 = CollisionForces.get(j);

            double elasticity = .95;

            boolean xBounce = false;

            // they've bounced horizontally

            if (element1.intersects(element2)) {
               if (element1.x < element2.x && element1.x + element1.w > element2.x) {
                  double difference = Math.abs(element2.x - (element1.x + element1.w));
                  collisionForce1.x -= difference;
                  xBounce = true;
               }

               if (element2.x < element1.x && element2.x + element2.w > element1.x) {
                  double difference = Math.abs(element1.x - (element2.x + element2.w));
                  collisionForce2.x += difference;
                  xBounce = true;
               }

               if (xBounce) {
                  velocity1.x = -velocity1.x * elasticity;
                  velocity2.x = -velocity2.x * elasticity;
               }

               boolean yBounce = false;

               // they've bounced vertically
               if (element1.y < element2.y && element1.y + element1.h > element2.y) {
                  double difference = Math.abs(element2.y - (element1.y + element1.h));
                  collisionForce1.y -= difference;
                  yBounce = true;
               }

               if (element2.y < element1.y && element2.y + element2.h > element1.y) {
                  double difference = Math.abs(element1.y - (element2.y + element2.h));
                  collisionForce2.y += difference;
                  yBounce = true;
               }

               if (yBounce) {
                  velocity1.y = -velocity1.y * elasticity;
                  velocity2.y = -velocity2.y * elasticity;
               }
            }
         }

         element1.x += collisionForce1.x;
         element1.y += collisionForce1.y;

         collisionForce1.x = 0;
         collisionForce1.y = 0;
      }


   }
}
