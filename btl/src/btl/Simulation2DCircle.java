package btl;



import java.awt.*;
import java.awt.geom.Point2D;


public class Simulation2DCircle extends Simulation2D {

	public Simulation2DCircle(int radius, int numSens, int sensRange, Image g, Main1 p) {
		
		super(radius, numSens, sensRange, g, p);

		boundary = new CircleBoundary(new Point2D.Double(0.0, 0.0), radius);

		for (int i = 0; i < numSensors; i++) {

			double x;
			double y;

			do {
				x = ((Math.random() - 0.5) * radius * 2.0);
				y = ((Math.random() - 0.5) * radius * 2.0);
			} while (Math.sqrt(x * x + y * y) > radius);

			this.sensors[i] = new Sensor2D(new Point2D.Double(x, y), sensorRange);
		}
	}


	public boolean outOfBounds(Point2D[] pts, int num) {
		for (int i = 1; i < num; i++) {
			if (Math.sqrt(Math.pow(pts[0].getX() - pts[i].getX(), 2) + Math.pow(pts[0].getY() - pts[i].getY(), 2)) > 1.00001 * width) {
				return true;
			}
		}
		return false;
	}

	public void logSim() {
		Main1.writeFile("Simulation: 2D Circle");
		Main1.writeFile("======================");
		Main1.writeFile("");
		if (optDistance != 0) {
			Main1.writeFile("Min-Sum:  " + String.valueOf(optDistance));
			Main1.writeFile("Min-Max:  " + String.valueOf(optMax));
			Main1.writeFile("Feasible: " + String.valueOf(feasible()));
		}
		((CircleBoundary) boundary).log();

		for (int i = 0; i < numSensors; i++) {
			sensors[i].log();
		}
		Main1.writeFile("------------------------------------------------------");
		Main1.writeFile("");

	}
}
