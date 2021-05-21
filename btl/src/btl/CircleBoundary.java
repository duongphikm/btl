package btl;


import java.awt.*;

import java.awt.geom.Point2D;
//import btl.MainForm;

public class CircleBoundary extends Boundary {

	private Point2D center;
	private double radius;

	public CircleBoundary(Point2D c, int r) {
		center = c;
		radius = r;
	}

	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.drawOval((int) (center.getX() - radius), (int) (center.getY() - radius), (int) radius * 2, (int) radius * 2);
	}

	public double getWidth() {
		return (double) radius * 2;
	}

	public Point2D getCenter() {
		return center;
	}

	public Point2D moveToBorder(Point2D p) {
		double x = (p.getX() - center.getX());
		double y = (p.getY() - center.getY());
		double dist = Math.sqrt(x * x + y * y);

		return new Point2D.Double(((x * radius) / dist), ((y * radius) / dist));
	}

	public Point2D[] setNodes(Point2D[] nodes, int numNodes) {

		for (int i = 1; i < numNodes; i++) {
			double xcomp = Math.cos(Math.acos((nodes[0].getX()) / radius) + (2.0 * Math.PI * i) / numNodes);
			double ycomp = Math.sin(Math.asin((nodes[0].getY()) / radius) + (2.0 * Math.PI * i) / numNodes);

			nodes[i] = new Point2D.Double((xcomp) * (radius), ((ycomp) * (radius)));
		}
		return nodes;
	}

	public void log() {

		Main1.writeFile("Circle Boundary");
		Main1.writeFile("center: (" + (String.valueOf(center.getX())) + ", " + (String.valueOf(center.getY())) + ")");
		Main1.writeFile("radius:    " + (String.valueOf(radius)));
		Main1.writeFile("");

	}
}
