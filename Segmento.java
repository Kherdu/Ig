package model;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLContext;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GL2;
import javax.media.opengl.fixedfunc.GLMatrixFunc;
import javax.media.opengl.glu.GLU;




public class Segmento {

	private Dot _dot1;
	private Dot _dot2;
	
	public Segmento(Dot a,Dot b){
		this._dot1=a;
		this._dot2=b;
	}

	public Dot get_dot1() {
		return _dot1;
	}

	public Dot get_dot2() {
		return _dot2;
	}
}
