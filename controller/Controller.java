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

import com.jogamp.opengl.util.FPSAnimator;

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
	private FPSAnimator _timer;
	
	private final GLU glu = new GLU(); //This object is kept for invoking gluOrtho2D in update_PROJECTION_MATRIX
	//private boolean update_Proyection_Matrix=false;
	
	
	public Controller(GLAutoDrawable canvas1){
		System.out.print("Into Controller's constructor\n\n");
		
		canvas= canvas1;		      
		double xLeft, xRight, yTop, yBottom;
		xLeft=0; xRight= (double)canvas.getWidth();
        yBottom=0;  yTop= (double)canvas.getHeight();
		scene= new Scene(xLeft, xRight, yTop, yBottom); //Initialize the scene size with the viewport size 
		_timer = new FPSAnimator(canvas,1);
		_timer.start();
		_timer.pause();
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
        
        if(!_timer.isPaused()) 
        	scene.step(); 					 //Actualizar la escena
        scene.draw(drawable);				 //Visualizar la escena
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
			case KeyEvent.VK_C:		scene.opcion(0);		 break; //centrar camara
			case KeyEvent.VK_S:		scene.opcion(1);		 break; //seleccion
			case KeyEvent.VK_P:		scene.opcion(2);		 break; //pintar segmento			
			case KeyEvent.VK_A:		if(_timer.isPaused()){
										
										_timer.resume();
									}else 
										_timer.pause();		 break;	//animar/parar la animacion
									
			}
		  
		postEventos();
		
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
		
		postEventos();
		
		
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
		
		postEventos();
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
	
	
	
	/**
	 *   metodo auxiliar que se ejecuta despues
	 * de cada evento para actualizar la escena
	 */
	private void postEventos(){
		
		//no tocar este trozo de codigo(no se lo que hace pero nos lo dio el profe)
		GLContext context=canvas.getGL().getGL2().getContext();
		  if (!context.isCurrent()) context.makeCurrent();
		  update_PROJECTION_MATRIX(canvas);
		context.release();		  
		
		//escribir codigo por debajo de este comentario
		
		
		
		
		// no tocar esta ultima linea
		canvas.display();
	}

	
}