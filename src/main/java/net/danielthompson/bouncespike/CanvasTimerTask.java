package net.danielthompson.bouncespike;

import java.awt.*;
import java.util.TimerTask;

public class CanvasTimerTask extends TimerTask {
   private final Canvas _canvas;
   private final Graphics _graphics;

   public CanvasTimerTask(Canvas canvas, Graphics graphics) {
      _canvas = canvas;
      _graphics = graphics;
   }

   @Override
   public void run() {
      if (_canvas != null)
         _canvas.update(_graphics);
   }
}
