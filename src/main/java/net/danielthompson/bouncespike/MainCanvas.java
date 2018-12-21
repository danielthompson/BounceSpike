package net.danielthompson.bouncespike;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class MainCanvas extends Canvas {

   private final BufferedImage _bufferedImage;
   private final Graphics2D _graphics2d;
   private final Color _backgroundColor = new Color(32, 32, 32);

   private final SimulationState _simulationState;
   private final int _pixelColor = Color.WHITE.getRGB();

   private final int _width;
   private final int _height;

   public MainCanvas(BufferedImage bufferedImage, SimulationState simulationState) {
      _bufferedImage = bufferedImage;
      _graphics2d = _bufferedImage.createGraphics();
      _simulationState = simulationState;
      _width = _bufferedImage.getWidth();
      _height = _bufferedImage.getHeight();
      fillBackground();
   }

   private void fillBackground() {
      _graphics2d.setPaint(_backgroundColor);
      _graphics2d.fillRect(0, 0, _width, _height);
   }

   public void paint(Graphics g) {

      fillBackground();

      int x, y;

      for (Point point : _simulationState.Points) {
         x = (int)point.x;
         y = (int)point.y;

         if (x < 0 || x >= _width || y < 0 || y >= _height)
            continue;

         _bufferedImage.setRGB(x, (int)point.y, _pixelColor);
      }

      _graphics2d.setPaint(Color.WHITE);
      for (Circle circle : _simulationState.Circles) {
         _graphics2d.draw(new Ellipse2D.Double(circle.x, circle.y, circle.r, circle.r));
      }

      for (int i = 0; i < _simulationState.Boxes.size(); i++) {
         Box box = _simulationState.Boxes.get(i);
         _graphics2d.setPaint(Color.WHITE);
         _graphics2d.drawRect((int)box.x, (int)box.y, (int)box.w, (int)box.h);
         _graphics2d.setPaint(box.color);
         _graphics2d.fillRect((int)box.x + 1, (int)box.y + 1, (int)box.w - 1, (int)box.h - 1);
         _graphics2d.setPaint(Color.WHITE);
         _graphics2d.drawString(String.valueOf(i), (int) (box.x + box.w / 2), (int) (box.y + box.h / 2));

      }

      g.drawImage(_bufferedImage, 0, 0, Color.magenta, null);
   }

   public void update(Graphics g) {
      paint(g);
   }

}
