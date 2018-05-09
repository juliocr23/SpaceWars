package Entity;

import java.awt.*;

public class Rectangle
{
  public double x;
  public double y;
  public int width;
  public int height;

   double vx = 0;
   double vy = 0;

   static final double g = .1;

   double ax = 0;
   double ay = g;

   boolean held = false;

   public Rectangle(double x, double y, int width, int height)
   {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
   }

   public void setLocation(double x, double y)
   {
      this.x = x;
      this.y = y;
   }

   public void grab()
   {
      held = true;
   }

   public void drop()
   {
      held = false;
   }

   public boolean overlaps(Rectangle r)
   {
      return (x      < r.x + r.width)   &&
             (x + width > r.x       )   &&
             (y      < r.y + r.height)   &&
             (y + height > r.y       );
   }

   public boolean contains(double mx, double my)
   {
      return (mx > x) && (mx < x+ width) && (my > y) && (my < y+ height);
   }


   public void draw(Graphics g)
   {
      g.drawRect((int)x, (int)y, width, height);
   }

   public void setAcceleration(double ax, double ay)
   {
      this.ax = ax;
      this.ay = ay;
   }

   public void setVelocity(double vx, double vy)
   {
      this.vx = vx;
      this.vy = vy;
   }

   public void move()
   {
      vx += ax;
      vy += ay;

      x += vx;
      y += vy;
   }


   public void moveBy(double dx, double dy)
   {
      x += dx;
      y += dy;
   }

   public void resizeBy(int dw, int dh)
   {
       width += dw;
       height += dh;
   }


}