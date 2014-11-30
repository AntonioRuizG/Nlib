/**
 * 
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

	/**
	 * @param context Contexto de la aplicacion(MainActivity)
	 */
	public NlibRenderer(Context context) {
		this.context = context;
	}
	
	@Override
	public void onDrawFrame(GL10 unused) {
		glClear(GL_COLOR_BUFFER_BIT);
		
	}
	
	/**
	 * 
	 */
	@Override
	public void onSurfaceChanged(GL10 unused, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * 
	 */
	@Override
	public void onSurfaceCreated(GL10 unused, EGLConfig arg1) {
		float alpha = 1;
		float blue = 0.8f;
		float green = 0.5f;
		float red = 0;
		glClearColor(red, green, blue, alpha);
		
	}

	/**
	 * @return the aplication context
	 */
	public final Context getContext() {
		return context;
	}

}
