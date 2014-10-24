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
	
	/////////////////////////////////
	public Scene(double xLeft1, double xRight1, double yTop1, double yBottom1){
		// SVA
		xLeft= xLeft1;
		xRight= xRight1;
		yTop= yTop1;
		yBottom= yBottom1;
		
		//mouse coordinates
		Mx=0;
		My=0;
		
		windowX= xRight-xLeft;
		windowY= yTop-yBottom;
		// Triangle size
		
		triangleWidth= 0.4*(xRight-xLeft);
        triangleHeight= 0.8*(yTop-yBottom);
        // Triangle initial location
    
        xTriangle= xLeft + 0.3*(xRight-xLeft);
        yTriangle= yBottom + 0.1*(yTop-yBottom);	
        
        redColor= true;
	     
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
			     yTop +=c;
			     yBottom -=c;	     
			     
		}
		else{
				// Increase SVA width
			double newWidth= viewPortRatio*(yTop-yBottom);
				
			c=(newWidth-getWidth())/2;
				xRight+= c;
				xLeft -= c;		
		}
		
	}

	
	public void windowSize(int h, int w){
		
		windowX=w;
		windowY=h;
		
	}
	/////////////////////////////////
	public void draw(GLAutoDrawable drawable){
		
		GL2 gl = drawable.getGL().getGL2();
        
        if(redColor) gl.glColor3f(1.0f,0.0f,0.0f);
        else gl.glColor3f(0.0f,1.0f,0.0f); 

        gl.glBegin(GL.GL_LINE_LOOP);
        	gl.glVertex2d( xTriangle, yTriangle );
	        gl.glVertex2d( xTriangle + triangleWidth, yTriangle );
	        gl.glVertex2d( xTriangle + triangleWidth/2.0, yTriangle + triangleHeight );
        gl.glEnd();

        gl.glBegin(GL.GL_POINTS);
			gl.glVertex2d(Mx, My);
		gl.glEnd();
        	
    
       
        
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
		double xcen = (xRight+xLeft)/2;
		double ycen = (yTop+yBottom)/2;
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
		
		
		
	}
	/////////////////////////////////
	public void changeColor(){
		redColor = !redColor;
	}

}