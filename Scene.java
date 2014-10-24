//////////////////////////////////////////// 
// Project skeleton for Computer 2D Graphics
// MVC-based design
// Author: Chus Martín
// 2014
//////////////////////////////////////////// 

package model;

//JOGL imports
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
		
		if (sceneVisibleAreaRatio>=viewPortRatio){
			     // Increase SVA height
			     double newHight= (xRight-xLeft)/viewPortRatio;
			     double yCenter= (yBottom+yTop)/2.0;
			     yTop= yCenter + newHight/2.0;
			     yBottom= yCenter - newHight/2.0;
		}
		else{
				// Increase SVA width
				double newWidth= viewPortRatio*(yTop-yBottom);
				double xCenter= (xLeft+xRight)/2.0;
				xRight= xCenter + newWidth/2.0;
				xLeft= xCenter - newWidth/2.0;
		}
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
		double width = this.getWidth();
		double height = this.getHeight();
		width = width*zoom;		
		height = height*zoom;
		xLeft= xcen-width/2;
		xRight= xcen+width/2;
		yTop= ycen+height/2;
		yBottom= ycen-height/2;
	}
	
	public void mouseDot(double mouseX, double mouseY){
		//las coordenadas del raton van al reves?
		Mx=xRight-mouseX;
		My=yTop-mouseY;
		
		
		
	}
	/////////////////////////////////
	public void changeColor(){
		redColor = !redColor;
	}

}