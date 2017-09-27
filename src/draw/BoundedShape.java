package draw;

import java.awt.Color;
import java.awt.Graphics;


abstract class NoneLineShape extends Shape
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean fill;
    

    public NoneLineShape()
    {
        super();
        fill=false;
    }
    

    public NoneLineShape(int x1, int y1, int x2, int y2, Color color, boolean fill)
    {
        super(x1, y1, x2, y2, color);
        this.fill=fill;
    }
    

    public void setFill(boolean fill)
    {
        this.fill=fill;
    }
    

    public int getUpperLeftX()
    {
        return Math.min(getX1(),getX2());
    }
    

    public int getUpperLeftY()
    {
        return Math.min(getY1(),getY2());
    }
    

    public int getWidth()
    {
        return Math.abs(getX1()-getX2());
    }
    

    public int getHeight()
    {
        return Math.abs(getY1()-getY2());
    }
    

    public boolean getFill()
    {
        return fill;
    }
    

  abstract public void draw( Graphics g );
}
