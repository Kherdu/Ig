//////////////////////////////////////////// 
// Project skeleton for Computer 2D Graphics
// MVC-based design
// Author: Chus Martín
// 2014
//////////////////////////////////////////// 

package model;

//JOGL imports

import java.util.ArrayList;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;

import com.jogamp.opengl.util.Animator;
import com.jogamp.opengl.util.FPSAnimator;

public class Scene {

	// Scene Visible Area (SVA)
	private double xLeft, xRight, yTop, yBottom; // SVA position
	
	// Scene variables
	private double xTriangle, yTriangle;			// posiciones de ejemplo
	private double triangleWidth, triangleHeight;	//ancho y largo de ejenplo
	private double windowX, windowY ;				//ancho y largo actual de la ventana
	private double xCenter,yCenter;					//centro actual de la escena
	private ArrayList<Poligono> _poligono;			//la lista de poligones
	private Segmento _seg;							//el segmento que maneja el usuario
	private PV2D _dSeg;								//el punto del segmento, usado meramente para memoria
//	private boolean _flip;							//booleano para indicar si ya hay un punto
	private int _option;							//la opccion seleccionada para el funcionamiento del clik 
	private int _polSelecionado;					//indica la posiccion del poligono selecionado en el array de poligonos,
													//-1 en caso de no haber ninguno selecionado
	private PV2D _CenCirculo;						//el centro del circulo que vamos a animar
	private Poligono _Circulo;						//el circulo que vamos a animar
	
	/////////////////////////////////
	public Scene(double xLeft1, double xRight1, double yTop1, double yBottom1){
		
		// SVA
		xLeft= xLeft1;
		xRight= xRight1;
		yTop= yTop1;
		yBottom= yBottom1;
		
		//SVA center
		xCenter=(xRight-xLeft)/2;
		yCenter=(yTop-yBottom)/2;
		
	
		//window size
		windowX= xRight-xLeft;
		windowY= yTop-yBottom;
		
		//_flip=true;
		_option=0;
		_polSelecionado = -1;
		
		_poligono=new ArrayList<Poligono>();
		triangleWidth= 0.4*(xRight-xLeft);
		xTriangle= xLeft + 0.3*(xRight-xLeft);
		/*Dot p1 = new Dot(xTriangle,yTriangle);
        Dot p2 = new Dot(xTriangle + triangleWidth,yTriangle);
        Segmento seg = new Segmento(p1,p2);*/
		//poligono 1
		PV2D p1= new PV2D(xTriangle,yTriangle,false);
		PV2D p2= new PV2D(xCenter,yCenter,false);
		PV2D p3= new PV2D(xCenter-(windowX/4),yCenter+(windowY/4),false);

		
		
        Poligono pol1= new Poligono(p1,100,6);
        Poligono pol2= new Poligono(p2,65,3);
        Poligono pol3= new Poligono(p3,50,4);
        _poligono.add(pol1);
        _poligono.add(pol2);
        _poligono.add(pol3);
		
	     
	}
	
	/////////////////////////////////
	public double getXLeft()   { return xLeft;}
	public double getXRight()  { return xRight;}
	public double getYTop()    { return yTop;}
	public double getYBottom() { return yBottom;}
	public double getWidth()   { return xRight-xLeft;}
	public double getHeight()  { return yTop-yBottom;}
	
    /////////////////////////////////
	public void resize(double viewPortRatio){		
		
		double sceneVisibleAreaRatio=(xRight-xLeft)/(yTop-yBottom);
		double c;
		
		if (sceneVisibleAreaRatio>=viewPortRatio){
			  
			// Increase SVA height
			double newHeight= (xRight-xLeft)/viewPortRatio;
			     
			     c=(newHeight-getHeight())/2;
			     yTop    +=c;
			     yBottom -=c;	     
			     
		}
		else{
			// Increase SVA width
			double newWidth= viewPortRatio*(yTop-yBottom);
			//double newWidth= viewPortRatio*(windowY);
			c=(newWidth-getWidth())/2;
				xRight+= c;
				xLeft -= c;		
		}
		
	}

	
	public void windowSize(int h, int w){
		
		windowX=w;
		windowY=h;
		System.out.println("ventana x: "+w+ " ventana y: "+h);
		System.out.println("SVA x: "+getWidth()+ " ventana y: "+getHeight());
	}
	/////////////////////////////////
	public void draw(GLAutoDrawable drawable){
		
		GL2 gl = drawable.getGL().getGL2();
         

		for(int i=0;i<_poligono.size();i++){
			if(i!=_polSelecionado) gl.glColor3f(1.0f, 0.0f , 0.0f);
			else gl.glColor3f(0.0f,0.0f,1.0f); 
        	gl.glBegin(GL.GL_LINE_LOOP);
		    	//gl.glVertex2d(_poligono.get(i).getdot1(0).get_x(), _poligono.get(i).getdot1(0).get_y());
		    	for(int j=0;j<_poligono.get(i).getPoligonoSize();j++){
		    		gl.glVertex2d(_poligono.get(i).getdot(j).get_x(), _poligono.get(i).getdot(j).get_y());
		    	}
		    gl.glEnd();
		}
		
		if(_seg!=null){
			//pintado del segmento
			gl.glBegin(GL.GL_LINE_STRIP);
       			gl.glColor3f(0.0f,1.0f,0.0f);
       			gl.glVertex2d(_seg.get_dot().get_x(),_seg.get_dot().get_y());
       			gl.glVertex2d(_seg.get_vector().get_x()+_seg.get_dot().get_x(),_seg.get_vector().get_y()+_seg.get_dot().get_y());
      
       		gl.glEnd();
       		
       		//pintado de los puntos de corte
       		if(_polSelecionado!=-1){
         	   Params tParams = new Params(0,1);
         	   for (int i = 0;i<this._poligono.size();i++){
         		   if(this._polSelecionado==i && _poligono.get(i).cyrusBeck(_seg, tParams)){
         			   gl.glBegin(GL.GL_POINTS);
         			   		gl.glColor3f(1.0f,0.0f,1.0f);
         			   		if(tParams.getin()>0)
         			   			gl.glVertex2d(_seg.get_dot().get_x()+(_seg.get_vector().get_x()*tParams.getin()),_seg.get_dot().get_y()+(_seg.get_vector().get_y()*tParams.getin()));
         			   		if(tParams.getout()<1)
         			   			gl.glVertex2d(_seg.get_dot().get_x()+(_seg.get_vector().get_x()*tParams.getout()),_seg.get_dot().get_y()+(_seg.get_vector().get_y()*tParams.getout()));
         			   gl.glEnd();
         		   }
         	   }
            }
       		
       		//pintado de la circunferencia
       		
       		if(_Circulo!=null){
       			gl.glBegin(GL.GL_LINE_LOOP);
       				for(int i = 0;i<_Circulo.getPoligonoSize();i++){       				
       					gl.glVertex2d(_Circulo.getdot(i).get_x(), _Circulo.getdot(i).get_y());       				
       				}
       			gl.glEnd();
       		}
       		
       		
       }
       
       
       
       
        /*gl.glBegin(GL.GL_POINTS);
    		gl.glVertex2d(Mx,My);
    	gl.glEnd();
    	*/
        gl.glFlush();
	}
	
	
	
	
	/////////////////////////////////
	public void moveTriangle(double xShift, double yShift){
		xTriangle += xShift;
		yTriangle += yShift;
	}
	
	public void moveScene(double x, double y){
		
		xLeft+= x;
		xRight+= x;
		yTop+= y;
		yBottom+= y;
		
		
	}
	public void zoomScene(double zoom){
		//implementar zoom
		
		double oldWidth = getWidth();
		double oldHeight = getHeight();
		double newWidth = oldWidth*zoom;		
		double newHeight = oldHeight*zoom;
		
		xLeft= xLeft+(oldWidth-newWidth);
		xRight= xRight-(oldWidth-newWidth);
		yTop= yTop-(oldHeight-newHeight);
		yBottom= yBottom+(oldHeight-newHeight);
		//Mx+=width;
		//My+=height;
	}
	
	
	/**
	 * metodo encargado de los calculos con los clicks del raton
	 * @param mouseX - la cordenada x del click del raton
	 * @param mouseY - la cordenada y del click del raton
	 */
	public void mouseDot(double mouseX, double mouseY){
	
		double Mx=xLeft+mouseX*getWidth()/windowX;
		double My=yTop-mouseY*getHeight()/windowY;
		if (_option==0){ // centrar
			centerView(Mx,My);
		}
		else if(_option==1){ //seleccionar
			int i = 0;
			_polSelecionado = -1;
			while(i<_poligono.size() && _polSelecionado==-1){
				if(_poligono.get(i).puntointerno(Mx,My)){
					_polSelecionado=i;
				}
				
				i++;
			}
		}
		else if (_option==2){ // pintar segmento
			
			if(_dSeg==null){
				_dSeg=new PV2D(Mx,My,false);
				//_flip=false;
			}else{
				
				PV2D vSeg=new PV2D(Mx-_dSeg.get_x(),My-_dSeg.get_y(),true);
				_seg = new Segmento(_dSeg,vSeg);
				_CenCirculo = new PV2D(_dSeg.get_x(),_dSeg.get_y(),false);				
				_dSeg=null;
				
				//_flip=true;
			}
			
		}
	}
	
	

	public void centerView(double X, double Y){
		
		double a = getWidth()/2;
		double b = getHeight()/2;
		
		xCenter=X;
		yCenter=Y;
		
		xLeft= xCenter - a;
		xRight= xCenter + a;
		yTop= yCenter + b;
		yBottom= yCenter - b;
		
		
	}
	
	


	/**
	 * cambia la accion actual de clic izquierdo
	 * 		*0-centrar
	 * 		*1-seleccionar
	 * 		*2-pintar
	 * 		*3-animar
	 * @param sel - la nueva selecion
	 */
	public void opcion(int sel) {
		_option=sel;
		
	}

	/**
	 * cambia la escenaen un paso de animacion
	 * esto es mover el centro del circulo y dejar que el drawale lo pinte
	 */
	public void step() {
		
		if(_CenCirculo!=null){
			_Circulo = new Poligono(_CenCirculo,30,300);
			
			_CenCirculo.setCordenadas(_CenCirculo.get_x()+_seg.get_vector().get_x()/25, _CenCirculo.get_y()+_seg.get_vector().get_y()/25);
			if (Math.abs(_CenCirculo.get_x())>Math.abs(_seg.get_dot().get_x()+_seg.get_vector().get_x())){
				_CenCirculo.setCordenadas(_seg.get_dot().get_x(), _seg.get_dot().get_y());
			}
		}
		
	}

	

}