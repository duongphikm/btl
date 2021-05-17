package btl;

import java.awt.*;

public abstract class Simulation {
	protected int numSensors;
	protected int sensorRange;
	protected double width;
	protected double optDistance;
	protected double optMax;
	protected Boundary boundary;
	protected Image graph;
	protected Main1 parent;

	public abstract void draw(Graphics g);

	public abstract void min();

	public abstract void minmax();

	public abstract void reset();

	public void logSim() {
	}
}
