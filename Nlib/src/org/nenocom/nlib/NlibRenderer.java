/**
 * If debugging is the process of removing bugs, then programming must be the process of putting them in.
 * Edsger W. Dijkstra
 */
package org.nenocom.nlib;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import org.nenocom.nlib.objects.PhongMonkey;
import org.nenocom.nlib.objects.PhongObject;
import org.nenocom.nlib.utils.MMatrix;
import org.nenocom.nlib.utils.ObjLoader;

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
	//private ColorLightObject esfera;
	private PhongObject monkey;
	
	/**
	 * @param context Contexto de la aplicacion(MainActivity)
	 */
	public NlibRenderer(Context context) {
		this.context = context;
		ObjLoader.setContext(context);
		ObjLoader.putObject("monkey", R.raw.monkey);
	}
	
	@Override
	public void onDrawFrame(GL10 unused) {
		glClear(GL_COLOR_BUFFER_BIT);
		
		monkey.onDrawFrame();
		monkey.rotate(1, 0, 1, 0);
		
		
	}
	
	/**
	 * 
	 */
	@Override
	public void onSurfaceChanged(GL10 unused, int widht, int height) {
		glViewport(0, 0, widht, height);
		MMatrix.setPerspective(projectionMatrix, 45, (float) widht / (float) height, 1f, 100000f);
		monkey.setProjectionMatrix(projectionMatrix);
		
	}
	
	/**
	 * 
	 */
	@Override
	public void onSurfaceCreated(GL10 unused, EGLConfig arg1) {
		float alpha = 1;
		float blue = 0.0f;
		float green = 0.0f;
		float red = 0.0f;
		glClearColor(red, green, blue, alpha);
		glEnable(GL_DEPTH_TEST);
		
		monkey = new PhongMonkey(this);
		monkey.translate(0, 0, -3f);
	}

	/**
	 * @return the aplication context
	 */
	public final Context getContext() {
		return context;
	}

}
