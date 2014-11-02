package model;
import java.util.*;


public class Copo {
	
	
		private ArrayList<Segmento> _lista;
		
		
	public Copo(){
		
		_lista= new ArrayList<Segmento>();
		
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
	
	public void addFractal(){
		/*
		Lapiz lap= new Lapiz();
		int repeticiones=5;
		for(int j=0;j<=repeticiones;j++){
			Copo aux = new Copo();
			for(int i=0;i<_lista.size();i++){
				_lista.get(i).fractalseg(aux, lap);
				
			}
			this._lista = aux._lista;
		}
		*/
		Lapiz lap= new Lapiz();
		
		ArrayList<Segmento> aux = new ArrayList<Segmento>();
		//probar con iteradores ArrayList<Segmento>  ;
		for(int i = 0;i<_lista.size();i++){		
			_lista.get(i).fractalseg(aux, lap);			
		}	
		_lista=aux;	
		
	}
	
	
	public void subFractal(){
		
 		if (_lista.size()>3){
		
			ArrayList<Segmento> aux = new ArrayList<Segmento>();
		
			for(int i = 0; i<_lista.size()-3;i+=4){
			
				Dot a = _lista.get(i).get_dot1();
				Dot b = _lista.get(i+3).get_dot2();
				Segmento c=new Segmento(a,b);
				aux.add(c);
			}
		
			_lista=aux;	
		}
	}
	
	
	
	
	
	
	
	
}
	