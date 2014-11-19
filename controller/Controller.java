//////////////////////////////////////////// 
// Project skeleton for Computer 2D Graphics
// MVC-based design
// Author: Chus Martín
// 2014
//////////////////////////////////////////// 

package controller;

//JOGL imports
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLContext;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GL2;
import javax.media.opengl.fixedfunc.GLMatrixFunc;
import javax.media.opengl.glu.GLU;

//AWT imports
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//Specific imports
import model.Scene;

public class Controller implements GLEventListener, KeyListener, MouseListener{

	private Scene scene; // The model which is controlled  
	private GLAutoDrawable canvas; // The viewport that is used to render the model
	
	private final GLU glu = new GLU(); //This object is kept for invoking gluOrtho2D in update_PROJECTION_MATRIX
	//private boolean update_Proyection_Matrix=false;
	
	
	public Controller(GLAutoDrawable canvas1){
		System.out.print("Into Controller's constructor\n\n");
		
		canvas= canvas1;		      
		double xLeft, xRight, yTop, yBottom;
		xLeft=0; xRight= (double)canvas.getWidth();
        yBottom=0;  yTop= (double)canvas.getHeight();
		scene= new Scene(xLeft, xRight, yTop, yBottom); //Initialize the scene size with the viewport size 
	    
		System.out.print("Scene bounds:\n");
	    System.out.print("xLeft:  \t" + xLeft +   " xRight:\t" +  xRight + "\n");
	    System.out.print("yBottom:\t" + yBottom + " yTop:  \t" +  yTop + "\n");
	    System.out.print("\n");

    }
	
		
	/////////////////////////////////
	// GLEventListener implementation
	@Override
	public void init(GLAutoDrawable drawable) {
		System.out.print("Into init\n\n");
		
		GL2 gl = drawable.getGL().getGL2();

		gl.glClearColor(1, 1, 1, 1);
		gl.glColor3f(0.0f,0.0f,1.0f); 

		gl.glPointSize(4.0f);
		gl.glLineWidth(2.0f);

		update_PROJECTION_MATRIX(drawable);
		
		gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
		gl.glLoadIdentity();
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		
		
		System.out.print("Into display\n");
		GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
       
        /*if (baldosas) scene.embaldosar(numCol);
        else scene.draw(drawable);
        */
        scene.draw(drawable);
	}
	
	

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		
		double viewPortRatio= (double)width/(double)height;		
		
		
		scene.windowSize(height, width);
		scene.resize(viewPortRatio);
		
		update_PROJECTION_MATRIX(drawable);
		
		
	}
		

	/////////////////////////////////
	// KeyListener implementation
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
		
		//modificar para mover escena en vez de figura
			case KeyEvent.VK_RIGHT: scene.moveScene(10,0);   break;
			case KeyEvent.VK_LEFT:  scene.moveScene(-10,0);  break;
			case KeyEvent.VK_UP:    scene.moveScene(0,10);   break;
			case KeyEvent.VK_DOWN:  scene.moveScene(0,-10);  break;
			case KeyEvent.VK_PLUS:  scene.zoomScene(0.9);    break;
			case KeyEvent.VK_MINUS: scene.zoomScene(1.1);    break;
			
			
		  
									
										
//down scene.zoom(0,9); update_projection_matrix= true; break;
//up scene.zoom (1,1); 
			 
		}
		  
		GLContext context=canvas.getGL().getGL2().getContext();
		  if (!context.isCurrent()) context.makeCurrent();
		  update_PROJECTION_MATRIX(canvas);
		context.release();
		  
		canvas.display();
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

	/////////////////////////////////
	// MouseListener implementation
	@Override
	public void mouseClicked(MouseEvent e) {
		
		double posx=e.getX();
		double posy=e.getY();
		
		scene.mouseDot(posx, posy);
		
		GLContext context=canvas.getGL().getGL2().getContext();
		  if (!context.isCurrent()) context.makeCurrent();
		  update_PROJECTION_MATRIX(canvas);
		context.release();
		  
		canvas.display();	
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	//////////////////////////////////
	// Specific methods for this class
	public void move(double xShift,double yShift){
		
		
		scene.moveScene(xShift,yShift);
		
		GLContext context=canvas.getGL().getGL2().getContext();
		  if (!context.isCurrent()) context.makeCurrent();
		  update_PROJECTION_MATRIX(canvas);
		context.release();
		  
		canvas.display();
	}
	
	
	
	
	
	private void update_PROJECTION_MATRIX(GLAutoDrawable drawable) {
		double xLeft= scene.getXLeft();
		double xRight= scene.getXRight();
		double yBottom= scene.getYBottom();
		double yTop= scene.getYTop();
				
		GL2 gl = drawable.getGL().getGL2();
		gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
        gl.glLoadIdentity();
		glu.gluOrtho2D(xLeft, xRight, yBottom, yTop);
	}

	
}