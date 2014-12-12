package model;

public class PV3D {

	private double _x;
	private double _y;
	private double _z;
	private boolean _vector;
	
	/**
	 * constructor basico, pide dos cordenadas y si ess un vector
	 * @param x - la coordenada x
	 * @param y - la cordenada y
	 * @param vector - booleano indicando si es un vector, true si lo es
	 */
	public PV3D(double x, double y,double z,boolean vector){
		this._x=x;
		this._y=y;
		this._z=z;
		this._vector=vector;
	}
	
	/**
	 * constructor por copia
	 * @param punto - el punto a copiar
	 */
	public PV3D(PV3D punto){
		this._x = punto.get_x();
		this._y = punto.get_y();
		this._z = punto.get_z();
		this._vector= punto.is_Vector();
	}

	/**
	 * geter de la cordenada x
	 * @return - la cordenada x del punto/vector
	 */
	public double get_x() {
		return _x;
	}

	/**
	 * geter de la cordenada y
	 * @return - la cordenada y del punto/vector
	 */
	public double get_y() {
		return _y;
	}
	/**
	 * geter de la cordenada z
	 * @return - la cordenada z del punto/vector
	 */
	public double get_z() {
		return _z;
	}
	/**
	 * devuelve el tipo de objeto que es, o punto o vector
	 * @return - True si es vector, false si es punto
	 */
	public boolean is_Vector(){
		return _vector;
	}
	
	/**
	 * metodo para cambiar el punto actual
	 * @param newDot - el nuevo punto
	 */	
	public void changeDot(PV3D newDot){
		_x=newDot.get_x();
		_y=newDot.get_y();
		_z=newDot.get_z();
	}
	
	/**
	 * metodo para normalizar un vector.(darle modulo 1)  
	 * 
	 * cambiarlo para que use las 3 coordenadas.
	 */
	public void normalizarVector(){
		double aux_x= _x/(Math.hypot(_x, _y));
		_y = _y/(Math.hypot(_x,_y));
		_x=aux_x;
	}
	
	public void setCordenadas(double x,double y, double z){
		this._x=x;
		this._y=y;
		this._z=z;
	}

}
