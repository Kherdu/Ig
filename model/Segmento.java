package model;

import java.util.ArrayList;



public class Segmento {

	private PV2D _dot;
	private PV2D _vector;
	
	/**
	 * constructor
	 * @param a
	 * @param b
	 */
	public Segmento(PV2D a,PV2D b){
		this._dot=a;
		this._vector=b;
	}

	public PV2D get_dot() {
		return _dot;
	}

	public PV2D get_vector() {
		return _vector;
	}
	
	
	/**
	 * metodo que devuelve la longitud del segmento
	 * @return - la longitud del segmento
	 */
	public double getSize(){
		
		double r = Math.hypot(_vector.get_x(), _vector.get_y());
		
		return r;
	}
	
	/**
	 * metodo que devuelve el angulo con el eje de cordenadas
	 * @return - el angulo relativo con el eje x que forma este segmento con aquel, por la izquierda
	 */
	public double getAngulo(){
		
		
		
		double mod = getSize();
		double v = _vector.get_x();		
		double r = v/mod;
		
		r= Math.acos(r);		
		r= Math.toDegrees(r);
		
		//1º cuadrante
		if(_vector.get_x()>0 && _vector.get_y()>0){
			
		}
		//2º cuadrante
		else if(_vector.get_x()<0 && _vector.get_y()>0){
			
		}
		//3º cuadrante
		else if(_vector.get_x()<0 && _vector.get_y()<0){
			
		}
		//4º cuadrante
		else if(_vector.get_x()>0 && _vector.get_y()<0){
			
		}
		
		
		
		return r;
			
	}
	
	
	/**
	 * Funcion que crea un el proximo segmento del poligono
	 * 
	 * gurada el fractal en el copo aux
	 * 
	 * 
	 * @param aux - la lista donde se guarda los nuevos segmentos
	 */
	public Segmento newSegmento(int lados, Lapiz lap){
	
		Segmento x;
		
		
		//inizializa el lapiz
		lap.moveLapiz(get_dot());
		double r=getAngulo();		
		lap.girarlapiz(r);
		lap.avanzar(getSize());		
		r=180-(360/lados);
		lap.girarx(r);
		x = lap.crearSegmento(getSize());
			
		return x;
		
		
	}
}
