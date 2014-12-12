package model;

import java.util.ArrayList;



public class Cara {

	private ArrayList<PV3D> _dots;
	private PV3D _normal;
	
	/**
	 * constructor por defecto
	 * @param a - punto principal del segmento
	 * @param b - el vector normal asociado al vector
	 */
	public Cara(ArrayList<PV3D> face){
		this._dots= new ArrayList<PV3D> ();
		_dots=face;
		_normal=NormalNewell(face);
	}

	
	

	
	public ArrayList<PV3D> get_dots() {
		return _dots;
	}




	public void set_dots(ArrayList<PV3D> _dots) {
		this._dots = _dots;
	}


	

	private PV3D NormalNewell(ArrayList<PV3D> face){
		
		PV3D norm=null;
		_normal=norm;
		return _normal;
		
	}
	
	
	
	
}
