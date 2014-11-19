package model;

public class Dot {

	private double _x;
	private double _y;
	
	public Dot(double x, double y){
		this._x=x;
		this._y=y;
	}

	public double get_x() {
		return _x;
	}

	public double get_y() {
		return _y;
	}
	public void moveDot(double x, double y){
		_x=x;
		_y=y;
	}
	
	public void changeDot(Dot newDot){
		_x=newDot._x;
		_y=newDot._y;
	}
	
	
}
