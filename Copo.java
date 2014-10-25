package model;
import java.util.*;


public class Copo {
	
	
		private List<Segmento> lista;
		
		
	public Copo(){
		
		List<Segmento> lista= new ArrayList<Segmento>();
		
	}
	public void addSegmento(Segmento s){
		
		lista.add(s);
		
	}
	
	public List getCopo(){
		
		return lista;
	}
	
	public void cambiaPuntos(){
		
		lista.add(index, element)
	}
}
