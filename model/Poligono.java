package model;


public class Poligono {
	
	
		private Segmento[] _lista;
		private PV2D[] _Normales;

		
	/**
	 * 	constructor de Poligonos
	 * @param c - centro de la circunferencia circunscrita
	 * @param r - radio de la circunferencia circunscrita
	 * @param l - lado numero de lados
	 */
	public Poligono(PV2D c, double r, int l ){
		_lista = new Segmento[l];
		_Normales = new PV2D[l];
		initPoligono(c,r,l);
	}
	
	/**
	 * metodo que devuelve el vector del segmento
	 * @param i - la posicion del segmento
	 * @return el vector del segmento pedido
	 */
	public PV2D getVector (int i){
		return _lista[i].get_vector();
	}
	
	/**
	 * metodo que devuelve el punto de un segmento
	 * @param i - la posicion del segmento
	 * @return el punto del segmento pedido
	 */
	public PV2D getdot (int i){
		return _lista[i].get_dot();
	}
	
	/**
	 * metodo que devuelve el tamaño del arraylist de segmentos
	 * @return - el arrayList de segmentos
	 */
	public int getPoligonoSize(){
		
		return _lista.length;
	}
	
	/**
	 * geter del vector de normales
	 * @return el vector de normales
	 */
	public PV2D[] getNormales(){
		return _Normales;
	}
	
	/**
	 * metodo auxiliar que crea el poligono a partir de una circunferencia
	 * @param c - el centro de la circunferencia que circuscribe la figura
	 * @param r - el radio de la circunferencia
	 * @param l - el numero de lados
	 */
	private void initPoligono( PV2D c, double r, int l){
		
		//calculo de las constantes pertinentes para la construccion del poligono 
		double alfa= (2*Math.PI)/l;     // angulo usado durante el giro del lapiz
		double gamma= (Math.PI-alfa)/2; // el angulo externo con respecto al radio
		double dis=r*Math.cos(gamma)*2; // el tamaño del triangulo
		gamma=Math.toDegrees(gamma);    // conversion a grados
		alfa=Math.toDegrees(alfa);
		
		
		// inizializacion del lapiz
		Lapiz lap= new Lapiz();
		lap.moveLapiz(c);
		lap.avanzar(r);
		lap.girarx(180-gamma);
		
		// creo el poligono
		for (int i=0;i<l;i++){
			Segmento s= lap.crearSegmento(dis);
			_lista[i] = s;
			PV2D vecNor= new PV2D(s.get_vector().get_y(),-s.get_vector().get_x(),true);
			vecNor.normalizarVector();
			_Normales[i] = vecNor;
			lap.girarx(alfa);
		}
		
	}
	
	
	/**
	 * Metodo para determinar si un punto esta dentro del una figura
	 * @param raton - el punto clicleado
	 * @return true si esta dentro, false si esta fuera
	 */
	public boolean puntointerno(double ratonx,double ratony){
		int i = 0;
		boolean dentro = true;
		double r;
		PV2D raton = new PV2D(ratonx,ratony,false);//el punto del raton
		while(i<_Normales.length && dentro){
			PV2D vectoraux = new PV2D(raton.get_x()-_lista[i].get_dot().get_x(),raton.get_y()-_lista[i].get_dot().get_y(),true);//el vector formado por el raton y el vertice
			r = angulo(_Normales[i],vectoraux); // calcula el angulo
			
			dentro = (r<=90 && r>=-90);// comprueba que esta dentro
			i++;
		}		
		
		return dentro;
	}
	
	
	/**
	 * 
	 * metodo auxiliar para calcular el angulo
	 * @return el angulo entre -180 y 180
	 */
	private double angulo(PV2D normal, PV2D pq){
		double num = ((-1*normal.get_x()*pq.get_x())+(-1*normal.get_y()*pq.get_y()));
		boolean signo = num>=0;//true si ess positivo o cero, flase en caso de ser negativo
		double dem = (Math.hypot(normal.get_x(),normal.get_y()))*(Math.hypot(pq.get_x(),pq.get_y()));
		double r = num/dem;
			
		r = Math.acos(r);
		r = Math.toDegrees(r);
		
		if(signo && (r<0))
			r = r*-1;
		else if(!signo && (r>=0))
			r = r*-1;
		
		
		return r;
	}
	
	

		
}
	