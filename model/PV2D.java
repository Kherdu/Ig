package model;

public class PV2D {

	private double _x;
	private double _y;
	private boolean _vector;
	
	
	public PV2D(double x, double y,boolean vector){
		this._x=x;
		this._y=y;
		this._vector=vector;
	}

	public double get_x() {
		return _x;
	}

	public double get_y() {
		return _y;
	}
	
	public boolean is_Vector(){
		return _vector;
	}
	
	public void changeDot(PV2D newDot){
		_x=newDot._x;
		_y=newDot._y;
	}

}
