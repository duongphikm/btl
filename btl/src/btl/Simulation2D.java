package btl;

import java.awt.*;

import java.awt.geom.Point2D;

public class Simulation2D extends Simulation {

	protected Sensor2D[] sensors;

	public Simulation2D(int radius, int numSens, int sensRange, Image g, Main1 p) {

		parent = p;
		width = (radius * 2);
		numSensors = numSens;
		sensorRange = sensRange;
		sensors = new Sensor2D[numSensors];
		graph = g;
	}

	public void minmax() {
		optDistance = width * numSensors;
		optMax = width * numSensors;

		Sensor2D[] sens = null;
		Sensor2D[] temp = null;

		
		for (int i = 0; i < numSensors; i++) {

			
			temp = initializeSensors(sensors, i);

			if (temp != null) {

				
				temp = minmaxSort(temp);

				double maxDistance = Sensor2D.maxDistance(temp, numSensors);
				double totalDistance = Sensor2D.totalDistance(temp, numSensors);

				if (maxDistance < optMax) {
					optMax = maxDistance;
					optDistance = totalDistance;
					sens = temp;
				}

			}

		}
		if (sens != null) {
			sensors = sens;
		}

//		parent.setLabels(2 * optDistance / width, 2 * optMax / width, feasible());
	}

	public void min() {
		optDistance = width * numSensors;
		optMax = width * numSensors;

		Sensor2D[] sens = null;
		Sensor2D[] temp = null;

		for (int i = 0; i < numSensors; i++) {

			temp = initializeSensors(sensors, i);

			
			if (temp != null) {

				
				temp = minSort(temp);

				double maxDistance = Sensor2D.maxDistance(temp, numSensors);
				double totalDistance = Sensor2D.totalDistance(temp, numSensors);

				
				if (totalDistance < optDistance) {
					optMax = maxDistance;
					optDistance = totalDistance;
					sens = temp;
				}

			}

		}
		if (sens != null) {
			sensors = sens;
		}

//		parent.setLabels(2 * optDistance / width, 2 * optMax / width, feasible());
	}

	public Sensor2D[] initializeSensors(Sensor2D[] sens, int s) {

		Sensor2D[] temp = new Sensor2D[numSensors];
		for (int i = 0; i < numSensors; i++) {
			temp[i] = new Sensor2D(sens[i]);
		}

		Point2D[] nodes = new Point2D[numSensors];

		temp[s].moveTo(boundary.moveToBorder(temp[s].getLocation()));

		nodes[0] = new Point2D.Double(temp[s].getLocation().getX(), temp[s].getLocation().getY());

		nodes = boundary.setNodes(nodes, numSensors);
		
		if (outOfBounds(nodes, numSensors)) {
			return null;
		}

		return sensorsToNodes(temp, nodes, s);

	}

	
	public boolean outOfBounds(Point2D[] pts, int num) {
		return false;
	}

	
	public Sensor2D[] minmaxSort(Sensor2D[] temp) {

		boolean sort = true;

		while (sort) {
			sort = false;

			for (int i = 0; i < numSensors; i++) {
				for (int j = i; j < numSensors; j++) {

					
					if (Math.max(temp[i].distanceMoved(), temp[j].distanceMoved()) > Math.max(
							temp[i].distanceFrom(temp[j].getLocation()), temp[j].distanceFrom(temp[i].getLocation()))) {
						Point2D p = new Point2D.Double(temp[i].getLocation().getX(), temp[i].getLocation().getY());
						temp[i].moveTo(temp[j].getLocation());
						temp[j].moveTo(p);
						sort = true;
					}
				}
			}
		}
		return temp;
	}

	private Sensor2D[] minSort(Sensor2D[] temp) {

		boolean sort = true;

		while (sort) {
			sort = false;

			for (int i = 0; i < numSensors; i++) {
				for (int j = i; j < numSensors; j++) {

					if (temp[i].distanceMoved() + temp[j].distanceMoved() > temp[i].distanceFrom(temp[j].getLocation())
							+ temp[j].distanceFrom(temp[i].getLocation())) {
						Point2D p = new Point2D.Double(temp[i].getLocation().getX(), temp[i].getLocation().getY());
						temp[i].moveTo(temp[j].getLocation());
						temp[j].moveTo(p);
						sort = true;
					}
				}
			}
		}
		return temp;
	}

	
	public boolean feasible() {
		return sensorRange * 2 > width * Math.PI / numSensors;
	}

	public Sensor2D[] sensorsToNodes(Sensor2D[] temp, Point2D[] nodes, int s) {

		int[] closestNode = new int[numSensors];
		double[] nodeDistance = new double[numSensors];

		boolean[] points = new boolean[numSensors];
		boolean[] sensorMoved = new boolean[numSensors];

		for (int i = 0; i < numSensors; i++) {
			sensorMoved[i] = false;
			points[i] = false;
		}

		points[0] = true;
		sensorMoved[s] = true;

	
		for (int i = 1; i < numSensors; i++) {

			for (int j = 0; j < numSensors; j++) {
				closestNode[j] = temp[j].closestNode(nodes, points, numSensors);
				nodeDistance[j] = temp[j].distanceFrom(nodes[closestNode[j]]);
			}

			double dist = width;
			int select = 0;

			for (int j = 1; j < numSensors; j++) {
				if (nodeDistance[j] < dist && !sensorMoved[j]) {
					select = j;
					dist = nodeDistance[j];
				}
			}
			temp[select].moveTo(nodes[closestNode[select]]);
			sensorMoved[select] = true;
			points[closestNode[select]] = true;
		}

		return temp;
	}

	public void reset() {
		for (int i = 0; i < numSensors; i++) {
			sensors[i].unmove();
		}
	}

	public void draw(Graphics g) {
		Graphics grap = graph.getGraphics();
		grap.setColor(Color.WHITE);
		grap.fillRect(0, 0, (int) width, (int) width);
		grap.translate((int) width / 2, (int) width / 2);
		boundary.draw(grap);
		for (int i = 0; i < numSensors; i++) {
			sensors[i].draw(grap);
		}
		g.drawImage(graph, 0, 0, 400, 400, null);

	}
}
