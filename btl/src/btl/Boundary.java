package btl;

import java.awt.*;
import java.awt.geom.Point2D;


public abstract class Boundary {

	public abstract void draw(Graphics g);
	public abstract Point2D moveToBorder(Point2D p);
	public abstract Point2D[] setNodes(Point2D[] n, int num);
	
}
