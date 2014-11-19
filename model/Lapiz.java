package model;



public class Lapiz {

	private PV2D _pos;
	private double _dir;
	
	/**
	 * constructor del lapiz
	 */
	public Lapiz(){
		 _pos = new PV2D(0,0,false);
		 _dir=0;
	}
	
	/**
	 * metodo que mueve el lapiz a una posicion, sin girar
	 * @param punto - el punto donde acaba
	 * 
	 */
	public void moveLapiz(PV2D punto){
		_pos.changeDot(punto);		
			
	}
	
	/**
	 * metodo para girar el lapiz sin moverlo de donde esta
	 * @param angulo el nuevo angulo(pueden ser valores mayores/menores a +- 360, se escala)
	 */
	public void girarlapiz(double angulo){
		//angulo=Math.toDegrees(angulo);
		if ((angulo<=360)&&(angulo>=-360)){
			_dir=angulo;
		}else {
			_dir=(angulo%360);
		}
	}
	
	/**
	 * gira en x grados a la izquierda o derecha
	 * @param x cuantos angulos gira(+ a la izquierda, - a la drecha)
	 */
	public void girarx(double x){
		_dir += x;
		if ((_dir>360)||(_dir<-360))
			_dir=(_dir%360);
	}
	
	/**
	 * avanza una distancia x, segun la direccion que mire
	 * @param dis - la distancia que recorre
	 */
	public void avanzar(double dis){
		double x = _pos.get_x()+ (dis*Math.cos(Math.toRadians(_dir)));
		double y = _pos.get_y()+ (dis*Math.sin(Math.toRadians(_dir)));
		PV2D av = new PV2D(x,y,false);
		_pos.changeDot(av);
	}
	
	/**
	 * devuelve la posicion actual del lapiz
	 * @return - el punto actual
	 */
	public PV2D get_pos(){
		return _pos;
	}
	
	/**
	 * devuelve el angulo actual del lapiz
	 * @return - el angulo actual
	 */
	public double get_angle(){
		return _dir;
	}
	
	public Segmento crearSegmento(double avan){
		PV2D a = new PV2D(_pos.get_x(),_pos.get_y(),false);
		avanzar(avan);
		PV2D b = new PV2D(_pos.get_x()-a.get_x(),_pos.get_y()-a.get_y(),true);
		return new Segmento(a,b);		
	}
	
	
	
	
	
	
}
