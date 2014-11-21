package model;
import java.util.*;


public class Poligono {
	
	
		private ArrayList<Segmento> _lista;
		private int lados;
		
		
	/**
	 * 	constructor de Poligonos
	 * @param c - centro de la circunferencia circunscrita
	 * @param r - radio de la circunferencia circunscrita
	 * @param l - lado numero de lados
	 */
	public Poligono(PV2D c, double r, int l ){
		
		lados=l;
		_lista= new ArrayList<Segmento>();		
		initPoligono(c,r,lados);
		
	}
	
	/**
	 * metodo que añade un segmento despues del anterior
	 * @param s - el segmento a añadir
	 */
	public void addSegmento(Segmento s){
		_lista.add(s);		
	}
	
	/**
	 * metodo que devuelve el vector del segmento
	 * @param i - la posicion del segmento
	 * @return el vector del segmento pedido
	 */
	public PV2D getVector (int i){
		return _lista.get(i).get_vector();
	}
	
	/**
	 * metodo que devuelve el punto de un segmento
	 * @param i - la posicion del segmento
	 * @return el punto del segmento pedido
	 */
	public PV2D getdot (int i){
		return _lista.get(i).get_dot();
	}
	
	/**
	 * metodo que devuelve el arraylist de segmentos
	 * @return - el arrayList de segmentos
	 */
	public ArrayList<Segmento> getPoligono(){
		
		return _lista;
	}
	
	/**
	 * metodo auxiliar que crea el poligono a partir de una circunferencia
	 * @param c - el centro de la circunferencia que circuscribe la figura
	 * @param r - el radio de la circunferencia
	 * @param l - el numero de lados
	 */
	private void initPoligono( PV2D c, double r, int l){
		
		double alfa= (2*Math.PI)/l; //angulo alfa
		double gamma= (Math.PI-alfa)/2;
		double dis=r*Math.cos(gamma)*2;
		gamma=Math.toDegrees(gamma);
		alfa=Math.toDegrees(alfa);
		
		
		
		Lapiz lap= new Lapiz();
		lap.moveLapiz(c);
		lap.avanzar(r);
		lap.girarx(180-gamma);
		
		for (int i=0;i<l;i++){
			Segmento s= lap.crearSegmento(dis);
			_lista.add(s);
			lap.girarx(alfa);
		}
		
	}

		
}
	