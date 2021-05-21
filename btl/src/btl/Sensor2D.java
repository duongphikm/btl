package btl;

import java.awt.*;

import java.awt.geom.Point2D;

public class Sensor2D extends Sensor {

	private Point2D location;
	private Point2D initLocation;

	public Sensor2D(Point2D l, int r) {
		location = new Point2D.Double(l.getX(), l.getY());
		initLocation = new Point2D.Double(l.getX(), l.getY());
		range = r;
	}

	public Sensor2D(int x, int y, int r) {
		this(new Point2D.Double(x, y), r);
	}

	public Sensor2D(Sensor2D s) {
		location = new Point2D.Double(s.location.getX(), s.location.getY());
		initLocation = new Point2D.Double(s.initLocation.getX(), s.initLocation.getY());
		range = s.range;
	}

	public void moveTo(Point2D l) {
		location = new Point2D.Double(l.getX(), l.getY());
	}

	public int closestNode(Point2D[] nodes, boolean[] points, int numNodes) {
		double distance = 999999;
		int cur = -1;

		for (int i = 0; i < numNodes; i++) {

			if ((Math.sqrt(Math.pow(nodes[i].getX() - location.getX(), 2) + Math.pow(nodes[i].getY() - location.getY(), 2))) < distance
					&& !points[i]) {
				distance = (Math.sqrt(Math.pow(nodes[i].getX() - location.getX(), 2) + Math.pow(nodes[i].getY() - location.getY(), 2)));
				cur = i;
			}
		}
		return cur;
	}

	public void unmove() {
		location = new Point2D.Double(initLocation.getX(), initLocation.getY());
	}

	public boolean moved() {
		return ((location.getX() != initLocation.getX()) || (initLocation.getY() != location.getY()));
	}

	public Point2D getLocation() {
		return new Point2D.Double(location.getX(), location.getY());
	}

	public double distanceFrom(Point2D p) {
		return Math.sqrt(Math.pow(p.getX() - initLocation.getX(), 2) + Math.pow(p.getY() - initLocation.getY(), 2));
	}

	public double distanceMoved() {
		return (Math.sqrt(Math.pow(location.getX() - initLocation.getX(), 2) + Math.pow(location.getY() - initLocation.getY(), 2)));
	}

	public void draw(Graphics g) {
		if (location != initLocation) {
			g.setColor(Color.LIGHT_GRAY);
			g.drawOval((int) initLocation.getX() - 2, (int) initLocation.getY() - 2, 4, 4);
			g.fillOval((int) initLocation.getX() - 2, (int) initLocation.getY() - 2, 4, 4);
			g.drawLine((int) location.getX(), (int) location.getY(), (int) initLocation.getX(), (int) initLocation.getY());
		}

		g.setColor(Color.BLACK);
		g.drawOval((int) location.getX() - 2, (int) location.getY() - 2, 4, 4);
		g.fillOval((int) location.getX() - 2, (int) location.getY() - 2, 4, 4);
		g.setColor(Color.CYAN);
		g.drawOval((int) location.getX() - range, (int) location.getY() - range, range * 2, range * 2);

	}
	public static double totalDistance(Sensor2D[] sens, int numSens) {
		double distance = 0;

		for (int i = 0; i < numSens; i++) {
			distance += sens[i].distanceMoved();
		}

		return distance;
	}
	public static double maxDistance(Sensor2D[] temp, int numSens) {
		double maxDistance = 0;

		for (int i = 0; i < numSens; i++) {
			if (temp[i].distanceMoved() > maxDistance) {
				maxDistance = temp[i].distanceMoved();
			}
		}

		return maxDistance;
	}

	public void log() {

		Main1.writeFile("Sensor");
		Main1.writeFile(
				"location:     (" + (String.valueOf(location.getX())) + ", " + (String.valueOf(location.getY())) + ")");
		Main1.writeFile("range:        " + (String.valueOf(range)));
		if (moved()) {
			Main1.writeFile("origin:       (" + (String.valueOf(initLocation.getX())) + ", "
					+ (String.valueOf(initLocation.getY())) + ")");
			Main1.writeFile("displacement: " + (String.valueOf(distanceMoved())));
		}

		Main1.writeFile("");

	}

}
