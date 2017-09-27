package draw;

import java.awt.Color;
import java.awt.Graphics;


public class Circle extends NoneLineShape
{ 

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Circle()
    {
        super();
    }
    

    public Circle( int x1, int y1, int x2, int y2, Color color, boolean fill )
    {
        super(x1, y1, x2, y2, color,fill);
    }
     

    @Override
    public void draw( Graphics g )
    {
        g.setColor( getColor() );
        if (getFill())
            g.fillOval( getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight() );
        else
            g.drawOval( getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight() );
        
    }
    
}