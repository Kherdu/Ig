package model;

public class Params {

	private double in;
	private double out;

	public Params(double i, double o) {
		in = i;
		out = o;
	}

	public double getin() {
		return in;
	}

	public double getout() {
		return out;
	}

	public void setIn(double in) {
		this.in = in;
	}

	public void setOut(double out) {
		this.out = out;
	}

}
