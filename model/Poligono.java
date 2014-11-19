package model;
import java.util.*;


public class Poligono {
	
	
		private ArrayList<Segmento> _lista;
		private int lados;
	/**
	 * 	
	 * @param c centro
	 * @param r radio
	 * @param l lado
	 */
	public Poligono(Dot c, double r, int l ){
		
		lados=l;
		_lista= new ArrayList<Segmento>();
		
		
		initPoligono(c,r,l);
		
	}
	
	/**
	 * metodo que añade un segmento despues del anterior
	 * @param s - el segmento a añadir
	 */
	public void addSegmento(Segmento s){
		_lista.add(s);		
	}
	
	/**
	 * metodo que devuelve el primer punto de un segmento
	 * @param i - la posicion del segmento
	 * @return el primer punto del segmento pedido
	 */
	public Dot getdot1 (int i){
		return _lista.get(i).get_dot1();
	}
	
	/**
	 * metodo que devuelve el segundo punto de un segmento
	 * @param i - la posicion del segmento
	 * @return el segundo punto del segmento pedido
	 */
	public Dot getdot2 (int i){
		return _lista.get(i).get_dot2();
	}
	
	public ArrayList<Segmento> getCopo(){
		
		return _lista;
	}
	
	
	private void initPoligono( Dot c, double r, int l){
		
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
			Dot p1 = new Dot(lap.get_pos().get_x(),lap.get_pos().get_y());
			lap.avanzar(dis);
			Dot p2 = new Dot(lap.get_pos().get_x(),lap.get_pos().get_y());
			Segmento s=new Segmento(p1,p2);
			_lista.add(s);
			lap.girarx(alfa);
		}
		
	}

		
}
	