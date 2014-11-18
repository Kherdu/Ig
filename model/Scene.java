//////////////////////////////////////////// 
// Project skeleton for Computer 2D Graphics
// MVC-based design
// Author: Chus Martín
// 2014
//////////////////////////////////////////// 

package model;

//JOGL imports
import java.awt.Canvas;

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
	private Copo _copo;
	
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
		
		// Triangle size
		triangleWidth= 0.4*(xRight-xLeft);
        triangleHeight= 0.8*(yTop-yBottom);
       
        // Triangle initial location
        xTriangle= xLeft + 0.3*(xRight-xLeft);
        yTriangle= yBottom + 0.1*(yTop-yBottom);	
        
        redColor= true;
		_copo = new Copo();
        Dot p1 = new Dot(xTriangle,yTriangle);
        Dot p2 = new Dot(xTriangle + triangleWidth,yTriangle);
        Dot p3 = new Dot(xTriangle + triangleWidth/2.0, yTriangle + triangleHeight);
        Segmento seg1 = new Segmento(p1,p2);
        Segmento seg2 = new Segmento(p2,p3);
        Segmento seg3 = new Segmento(p3,p1);
        _copo.addSegmento(seg1);
        _copo.addSegmento(seg2);
        _copo.addSegmento(seg3);
        
	     
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
        
        if(redColor) gl.glColor3f(1.0f,0.0f,0.0f);
        else gl.glColor3f(0.0f,1.0f,0.0f); 

        gl.glBegin(GL.GL_LINE_LOOP);
		    gl.glVertex2d(_copo.getdot1(0).get_x(), _copo.getdot1(0).get_y());
        	for(int i=0;i<_copo.getCopo().size();i++){
        		gl.glVertex2d(_copo.getdot2(i).get_x(), _copo.getdot2(i).get_y());
        	}
        	
       
        gl.glEnd();
        
        /*gl.glBegin(GL.GL_POINTS);
    		gl.glVertex2d(Mx,My);
    	gl.glEnd();
    	*/
        gl.glFlush();
	}
	
	public void addNivel(){
		
		_copo.addFractal();
		
	}
	
	public void subNivel() {
		_copo.subFractal();
		
		
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
	
	
	public void mouseDot(double mouseX, double mouseY){
		
		//Mx= (getWidth()*mouseX)/500;
		Mx=xLeft+mouseX*getWidth()/windowX;
		//Mx=xLeft+mouseX;
		My=yTop-mouseY*getHeight()/windowY;
		
		centerView(Mx,My);
		/*System.out.println("mx="+Mx +" My"+ My);
		System.out.println("yTop="+yTop +" yBottom"+ yBottom);
		System.out.println("xRight="+xRight +" xLeft"+ xLeft);
		*/
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
	
	/*public void embaldosar(int nCol){
		double sceneVisibleAreaRatio=(xRight-xLeft)/(yTop-yBottom);
		double w=getWidth();
		double h=w/sceneVisibleAreaRatio;
		
		for (int i=0; i<nCol;i++){
			double currentH=0;
			while ((currentH+h)<=getHeight()){
				GLAutoDrawable canvas= new canvas;
				
				
			}
		}
		
	}
	*/
	
	public void desembaldosar(){
		
	}	
	/////////////////////////////////
	public void changeColor(){
		redColor = !redColor;
	}

	

}