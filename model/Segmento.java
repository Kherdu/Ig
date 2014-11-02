package model;

import java.util.ArrayList;



public class Segmento {

	private Dot _dot1;
	private Dot _dot2;
	
	/**
	 * constructor
	 * @param a
	 * @param b
	 */
	public Segmento(Dot a,Dot b){
		this._dot1=a;
		this._dot2=b;
	}

	public Dot get_dot1() {
		return _dot1;
	}

	public Dot get_dot2() {
		return _dot2;
	}
	
	
	/**
	 * metodo que devuelve la longitud del segmento
	 * @return - la longitud del segmento
	 */
	public double getSize(){
		double y = _dot1.get_y()-_dot2.get_y();
		double x = _dot1.get_x()-_dot2.get_x();
		
		double r = Math.hypot(x, y);
		return r;
	}
	
	/**
	 * metodo que devuelve el angulo con el eje de cordenadas
	 * @return - el angulo relativo con el eje x que forma este segmento con aquel, por la izquierda
	 */
	public double getAngulo(){
		double cat;
		double hip = getSize();
		cat = _dot1.get_x()-_dot2.get_x();
		double r = cat/hip;
		r=Math.abs(r);
		r= Math.acos(r);		
		r= Math.toDegrees(r);
		
		
		//divido el problema segun el caso
		//caso 1,el 1º punto esta mas a la izquierda y es el mas bajo,
		//no hace nada por que lo calcula bien			
		
		
		//caso 2,el 1º punto esta mas a la derecha y es el mas bajo,
		//hay que calcular el complementario
		if((_dot1.get_x()>_dot2.get_x())&&(_dot1.get_y()<_dot2.get_y())){
			r = 180 - r;
		
		//caso 3,el 1º punto esta mas a la derecha y es el mas alto,
		//hay que sumarle 180º
		}else if((_dot1.get_x()>_dot2.get_x())&&(_dot1.get_y()>_dot2.get_y())){
			r=r+180;
			
		//caso 4,el 1º esta mas a la izquierda y es el mas alto,
		//hay que calcular el camplementario y sumarle 180º
		}else if((_dot1.get_x()<_dot2.get_x())&&(_dot1.get_y()>_dot2.get_y())){
			r = 180 - r;
			r= 180 + r;
			
		//caso 5, los puntos estan alineados respecto al eje x
		}else if(_dot1.get_x()==_dot2.get_x()){
			r=r+180;
			
		//caso 6, los puntos estan alineados respecto al eje y
		}else if(_dot1.get_y()==_dot2.get_y()){
			if(_dot1.get_x()>_dot2.get_x())
				r=r+180;
		}
		return r;
			
	}
	
	/**
	 * funcion para swapear los puntos del segmento
	 */
	public void swapDots(){
		Dot aux = _dot1;
		_dot1=_dot2;
		_dot2=aux;
	}
	
	/**
	 * Funcion que crea un fractal de un segmento de un triangulo, como base.
	 * 
	 * gurada el fractal en el copo aux
	 * 
	 * 
	 * @param aux - la lista donde se guarda los nuevos segmentos
	 */
	public void fractalseg(ArrayList<Segmento> aux, Lapiz lap){
	
		//los 5 puntos que habra al final, los dos que tenemos mas los tres finales
		//a y e son los puntos ariginales
		Dot a,e;
		Segmento x,y,z,w;
		
		a=get_dot1();
		e=get_dot2();
		
		
		//inizializa el lapiz
		lap.moveLapiz(get_dot1());
		double r=getAngulo();
		
		
		
		lap.girarlapiz(r);
		//lap.girarx(180);
		double dis=getSize()/3;
		
		//calcula el primer nuevo punto
		lap.avanzar(dis);
		Dot b= new Dot(lap.get_pos().get_x(),lap.get_pos().get_y());
		//crea un segmento nuevo y lo añade al nuevo copo
		x=new Segmento(a,b);
		aux.add(x);
				
		//calcula el segundo punto
		lap.girarx(-60);
		lap.avanzar(dis);
		Dot c= new Dot(lap.get_pos().get_x(),lap.get_pos().get_y());
		//crea un nuevo segmento unido por el anterior y lo añade al copo
		y=new Segmento(b,c);
		aux.add(y);
		
		//calcula el 3º punto
		lap.girarx(120);
		lap.avanzar(dis);
		Dot d= new Dot(lap.get_pos().get_x(),lap.get_pos().get_y());
		//crea y añade los dos ultimos segmentos. de modo que el ultimo termine donde termina el segmento ariginal
		z=new Segmento(c,d);
		aux.add(z);
		w=new Segmento(d,e);
		aux.add(w);	
		
	}
}
