package model;


public class Poligono {
	
	
		private Segmento[] _lista;
		//private ArrayList<Segmento> _lista;
		//private ArrayList<PV2D> _Normales;
		private PV2D[] _Normales;
		
	/**
	 * 	constructor de Poligonos
	 * @param c - centro de la circunferencia circunscrita
	 * @param r - radio de la circunferencia circunscrita
	 * @param l - lado numero de lados
	 */
	public Poligono(PV2D c, double r, int l ){
		//_Normales = new ArrayList<PV2D>();
		//_lista= new ArrayList<Segmento>();
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
		return _lista[_lista.length].get_vector();
	}
	
	/**
	 * metodo que devuelve el punto de un segmento
	 * @param i - la posicion del segmento
	 * @return el punto del segmento pedido
	 */
	public PV2D getdot (int i){
		return _lista[_lista.length].get_dot();
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
	public PV2D[] getNormale(){
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
			PV2D vecNor= new PV2D(-s.get_vector().get_y(),s.get_vector().get_x(),true);
			vecNor.normalizarVector();
			_Normales[i] = vecNor;
			lap.girarx(alfa);
		}
		
	}

		
}
	