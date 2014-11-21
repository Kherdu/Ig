package model;

public class PV2D {

	private double _x;
	private double _y;
	private boolean _vector;
	
	/**
	 * constructor basico, pide dos cordenadas y si ess un vector
	 * @param x - la coordenada x
	 * @param y - la cordenada y
	 * @param vector - booleano indicando si es un vector, true si lo es
	 */
	public PV2D(double x, double y,boolean vector){
		this._x=x;
		this._y=y;
		this._vector=vector;
	}
	
	/**
	 * constructor por copia
	 * @param punto - el punto a copiar
	 */
	public PV2D(PV2D punto){
		this._x = punto.get_x();
		this._y = punto.get_y();
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
	public void changeDot(PV2D newDot){
		_x=newDot.get_x();
		_y=newDot.get_y();
	}
	
	/**
	 * metodo para normalizar un vector.(darle modulo 1)
	 */
	public void normalizarVector(){
		double aux_x= _x/(Math.hypot(_x, _y));
		_y = _y/(Math.hypot(_x,_y));
		_x=aux_x;
	}

}
