package bounceThread;
import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 * The component that draws the balls.
 */
public class BallComponent extends JComponent
{
   /**
    * Add a ball to the panel.
    * @param b the ball to add
    */
   public void add(Ball b)
   {
      balls.add(b);
   }

   public void paintComponent(Graphics g)
   {
      Graphics2D g2 = (Graphics2D) g;
      for (Ball b : balls)
      {
         g2.fill(b.getShape());
      }
   }

   private ArrayList<Ball> balls = new ArrayList<Ball>();
}

