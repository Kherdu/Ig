package model;



public class Lapiz {

	private Dot _pos;
	private double _dir;
	
	/**
	 * constructor del lapiz
	 */
	public Lapiz(){
		 _pos = new Dot(0,0);
		 _dir=0;
	}
	
	/**
	 * metodo que mueve el lapiz a una posicion, sin girar
	 * @param punto - el punto donde acaba
	 * 
	 */
	public void moveLapiz(Dot punto){
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
		Dot av = new Dot(x,y);
		_pos.changeDot(av);
	}
	
	
	public Dot get_pos(){
		return _pos;
	}
	
	public double get_angle(){
		return _dir;
	}
	
	
	
	
	
	
	
	
}
