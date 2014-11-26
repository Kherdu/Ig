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

public class Scene {

	// Scene Visible Area (SVA)
	private double xLeft, xRight, yTop, yBottom; // SVA position
	
	// Scene variables
	private double xTriangle, yTriangle;
	private double triangleWidth, triangleHeight;
	private boolean redColor;
	private double Mx,My;
	private double windowX, windowY ;
	private double xCenter,yCenter;
	private ArrayList<Poligono> _poligono;
	private Segmento _seg;
	private PV2D _dSeg;
	private PV2D _vSeg;
	private boolean _flip;
	private int _selec;
	private int _polSelecionado;//indica la posiccion del poligono selecionado en el array de poligonos, -1 en caso de no haber ninguno selecionado
	
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
		
		//mouse coordinates
		Mx=xCenter;
		My=yCenter;
		
		//window size
		windowX= xRight-xLeft;
		windowY= yTop-yBottom;
		
		_flip=true;
		_selec=0;
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
		redColor= true;
		

        Poligono pol1= new Poligono(p1,100,6);
        Poligono pol2= new Poligono(p2,65,3);
        _poligono.add(pol1);
        _poligono.add(pol2);
		
	     
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
       gl.glBegin(GL.GL_LINE_STRIP);
       		gl.glVertex2d(_seg.get_dot().get_x(),_seg.get_dot().get_y());
       		gl.glVertex2d(_seg.get_vector().get_x()+_seg.get_dot().get_x(),_seg.get_vector().get_y()+_seg.get_dot().get_y());
       gl.glEnd();
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
	
		Mx=xLeft+mouseX*getWidth()/windowX;
		My=yTop-mouseY*getHeight()/windowY;
		if (_selec==0){ // centrar
			centerView(Mx,My);
		}
		else if(_selec==1){ //seleccionar
			int i = 0;
			_polSelecionado = -1;
			while(i<_poligono.size() && _polSelecionado==-1){
				if(_poligono.get(i).puntointerno(Mx,My)){
					_polSelecionado=i;
				}
				
				i++;
			}
		}
		else if (_selec==2){ // pintar segmento
			
			if(_flip){
				_dSeg=new PV2D(Mx,My,false);
				_flip=false;
			}else{
				
				_vSeg=new PV2D(Mx-_dSeg.get_x(),My-_dSeg.get_y(),true);
				_seg = new Segmento(_dSeg,_vSeg);
				_flip=true;
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
		
		
		//double xShift=(getWidth()/2)-X;
		//double yShift=(getHeight()/2)-Y;
		//xCenter=X;
		//yCenter=Y;
		//moveScene(xShift,yShift);
		//yBottom= yBottom-getHeight()+Y;
		
		
	}
	
	
	/////////////////////////////////
	public void changeColor(){
		redColor = !redColor;
	}

	public void seleccion(int sel) {
		_selec=sel;
		
	}

	

}