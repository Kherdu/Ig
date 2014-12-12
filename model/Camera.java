package model;

//JOGL imports
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.fixedfunc.GLMatrixFunc;
import javax.media.opengl.glu.GLU;


public class Camera {
	// Frustum parameters 
	private float xLeft, xRight, yTop, yBottom, near, far;	
	
	// View parameters
	private float[] eye;
	private float[] look;
	private float[] up; 

	/////////////////////////////////
	public Camera(float[] eye1, float[] look1, float[] up1,
    		      float xLeft1, float xRight1, float yTop1, float yBottom1, float near1, float far1){
    	eye= eye1; 
    	look= look1;
    	up= up1;    
    	
    	xLeft= xLeft1; xRight= xRight1;
    	yTop= yTop1; yBottom= yBottom1;
    	near= near1; far= far1;
    	
    }
    
	/////////////////////////////////
	public float[] getEye(){ return eye;}
    public float[] getLook(){ return look;}
    public float[] getUp(){ return up;}
    
    public void setEye(float[] eye1){ eye= eye1;} 
    public void setLook(float[] look1){ look= look1;} 
    public void setUp(float[] up1){ up= up1;} 
    
	
    /////////////////////////////////
    public void setProjection(GLAutoDrawable drawable){
		GL2 gl = drawable.getGL().getGL2();
		gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
	    gl.glLoadIdentity();
		gl.glOrtho(xLeft, xRight, yBottom, yTop, near, far);
	}
	
	
	/////////////////////////////////
	public void setView(GLAutoDrawable drawable, GLU  glu){
		GL2 gl = drawable.getGL().getGL2();
		gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
		gl.glLoadIdentity();
		glu.gluLookAt( eye[0],  eye[1],  eye[2], 
				      look[0], look[1], look[2], 
				        up[0],   up[1],   up[2]);
	}

	/////////////////////////////////
	public void reshape(float viewPortRatio){
		float frustumRatio=(xRight-xLeft)/(yTop-yBottom);
		
		if (frustumRatio>=viewPortRatio) {
			     //Increase yTop-yBot
			     float newHeight= (xRight-xLeft)/viewPortRatio;
			     float yCenter= (yBottom+yTop)/2.0f;
			     yTop=    yCenter + newHeight/2.0f;
			     yBottom= yCenter - newHeight/2.0f;
		}
		else {
			//Increase xRight-xLeft
			float newWidth= viewPortRatio*(yTop-yBottom);
			float xCenter= (xLeft+xRight)/2.0f;
			xRight= xCenter + newWidth/2.0f;
			xLeft=  xCenter - newWidth/2.0f;
		}

	}
	
	    
}
