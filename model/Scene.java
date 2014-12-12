//////////////////////////////////////////// 
// Project skeleton for Computer 3D Graphics
// MVC-based design
// Author: Chus Martín
// 2014
//////////////////////////////////////////// 

package model;

import javax.media.opengl.GL2;
import javax.media.opengl.GL2GL3;
import javax.media.opengl.GLAutoDrawable;

public class Scene {
	
	//Scene variables
	private double xPrism, yPrism, zPrism; //Lower corner
	private double prismWidth, prismHeight, prismDepth; //Prism size
	
	/////////////////////////////////
	public Scene(double xLeft, double xRight, double yTop, double yBottom, double near, double far){
		
		//Cube construction
		prismWidth= 0.4*(xRight-xLeft);
		prismHeight= 0.4*(yTop-yBottom);;
		prismDepth= 0.1* (far-near);
		
        //Centered at (0,0,0)
		xPrism= -prismWidth/2.0;
        yPrism= -prismHeight/2.0;
        zPrism= -prismDepth/2.0;
        
	}
	

	/////////////////////////////////
	public void draw(GLAutoDrawable drawable){
		GL2 gl = drawable.getGL().getGL2();

        gl.glBegin(GL2GL3.GL_QUADS);
        	//Down
		    gl.glColor3f(1.0f,0.0f,0.0f);
            gl.glNormal3d(0,-1,0);
	        gl.glVertex3d(xPrism, yPrism, zPrism);
	        gl.glVertex3d(xPrism+prismWidth, yPrism, zPrism);
	        gl.glVertex3d(xPrism+prismWidth ,yPrism, zPrism+prismDepth);
	        gl.glVertex3d(xPrism,yPrism, zPrism+prismDepth);

	        //Up
		    gl.glColor3f(1.0f,1.0f,0.0f);
            gl.glNormal3d(0,1,0);
	        gl.glVertex3d(xPrism, yPrism+prismHeight, zPrism);
	        gl.glVertex3d(xPrism, yPrism+prismHeight, zPrism+prismDepth);
	        gl.glVertex3d(xPrism+prismWidth, yPrism+prismHeight, zPrism+prismDepth);
	        gl.glVertex3d(xPrism+prismWidth, yPrism+prismHeight, zPrism);

	        //Front
		    gl.glColor3f(1.0f,1.0f,1.0f);
            gl.glNormal3d(0,0,1);
	        gl.glVertex3d(xPrism, yPrism, zPrism+prismDepth);
	        gl.glVertex3d(xPrism+prismWidth, yPrism, zPrism+prismDepth);
	        gl.glVertex3d(xPrism+prismWidth, yPrism+prismHeight, zPrism+prismDepth);
	        gl.glVertex3d(xPrism, yPrism+prismHeight, zPrism+prismDepth);

	        //Back
		    gl.glColor3f(0.0f,1.0f,1.0f);
            gl.glNormal3d(0,0,-1);
	        gl.glVertex3d(xPrism, yPrism, zPrism);
	        gl.glVertex3d(xPrism, yPrism+prismHeight, zPrism);
	        gl.glVertex3d(xPrism+prismWidth, yPrism+prismHeight, zPrism);
	        gl.glVertex3d(xPrism+prismWidth, yPrism, zPrism);
	        
        	//Left
		    gl.glColor3f(0.0f,0.0f,1.0f);
            gl.glNormal3d(-1,0,0);
	        gl.glVertex3d(xPrism, yPrism, zPrism);
	        gl.glVertex3d(xPrism, yPrism, zPrism+prismDepth);
	        gl.glVertex3d(xPrism, yPrism+prismHeight, zPrism+prismDepth);
	        gl.glVertex3d(xPrism, yPrism+prismHeight, zPrism);

	        //Right
		    gl.glColor3f(0.0f,1.0f,0.0f);
		    gl.glNormal3d(1,0,0);
	        gl.glVertex3d(xPrism+prismWidth, yPrism, zPrism);
	        gl.glVertex3d(xPrism+prismWidth, yPrism, zPrism+prismDepth);
	        gl.glVertex3d(xPrism+prismWidth, yPrism+prismHeight, zPrism+prismDepth);
	        gl.glVertex3d(xPrism+prismWidth, yPrism+prismHeight, zPrism);

        gl.glEnd();

	}
	
	/////////////////////////////////
	public void movePrism(double xShift, double yShift, double zShift){
		xPrism+= xShift;
		yPrism+= yShift;
		zPrism+= zShift;
	}
	
}