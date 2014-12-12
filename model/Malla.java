package model;

import java.util.ArrayList;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;


public class Malla {
	
	
		private ArrayList<Cara> _caras;
		private ArrayList<PV3D> _vertices;
		
	
	public Malla(ArrayList<Cara> face,ArrayList<PV3D> vertex ){
		
		_caras=new ArrayList<Cara>();
		_vertices=new ArrayList<PV3D>();
		_caras=face;
		_vertices=vertex;
	}

	public ArrayList<Cara> get_caras() {
		return _caras;
	}

	public void set_caras(ArrayList<Cara> _caras) {
		this._caras = _caras;
	}

	public ArrayList<PV3D> get_vertices() {
		return _vertices;
	}

	public void set_vertices(ArrayList<PV3D> _vertices) {
		this._vertices = _vertices;
	}
	
	
	public void PaintMail(GLAutoDrawable drawable){
		
		GL2 gl = drawable.getGL().getGL2();
		
		
	}
}
	