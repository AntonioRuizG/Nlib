/**
 * If debugging is the process of removing bugs, then programming must be the process of putting them in.
 * Edsger W. Dijkstra
 */
package org.nenocom.nlib;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import static android.opengl.GLES20.*;
import android.opengl.GLSurfaceView.Renderer;

/**
 * @author Antonio Ruiz
 *
 */
public class NlibRenderer implements Renderer {
	
	//Contexto de la aplicacion
	private final Context context;
	
	private float[] projectionMatrix = new float[16];
	private GlObject objeto;
	private GlObject objeto2;
	
	/**
	 * @param context Contexto de la aplicacion(MainActivity)
	 */
	public NlibRenderer(Context context) {
		this.context = context;
		
	}
	
	@Override
	public void onDrawFrame(GL10 unused) {
		glClear(GL_COLOR_BUFFER_BIT);
		
		objeto2.onDrawFrame();
		objeto.onDrawFrame();
		
		
	}
	
	/**
	 * 
	 */
	@Override
	public void onSurfaceChanged(GL10 unused, int widht, int height) {
		glViewport(0, 0, widht, height);
		MMatrix.setPerspective(projectionMatrix, 45, (float) widht / (float) height, 1f, 100000f);
		objeto.setProjectionMatrix(projectionMatrix);
		objeto2.setProjectionMatrix(projectionMatrix);
		
	}
	
	/**
	 * 
	 */
	@Override
	public void onSurfaceCreated(GL10 unused, EGLConfig arg1) {
		float alpha = 1;
		float blue = 0.0f;
		float green = 0.7f;
		float red = 1.0f;
		glClearColor(red, green, blue, alpha);
		glEnable(GL_DEPTH_TEST);
		objeto = new GraysKleynBottle(this);
		objeto.translate(4, 0, -15f);
		objeto.setDrawMode(GL_TRIANGLE_STRIP);
		
		objeto2 = new KleinBottle(this);
		objeto2.translate(-18, 0, -50f);
		objeto2.setDrawMode(GL_LINES);
	}

	/**
	 * @return the aplication context
	 */
	public final Context getContext() {
		return context;
	}

}
