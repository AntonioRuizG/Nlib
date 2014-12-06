/**
 * If debugging is the process of removing bugs, then programming must be the process of putting them in.
 * Edsger W. Dijkstra
 */
package org.nenocom.nlib;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import org.nenocom.nlib.test.GraysKleynBottle;
import org.nenocom.nlib.test.KleinBottle;
import org.nenocom.nlib.test.Sphere;
import org.nenocom.nlib.utils.MMatrix;

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
	//private ColorLightObject objeto;
	private ColorLightObject objeto2;
	//private ColorLightObject esfera;
	private PhongMonkey monkey;
	
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
		
		objeto2.onDrawFrame();
		//objeto.onDrawFrame();
		//esfera.onDrawFrame();
		monkey.onDrawFrame();
		monkey.rotate(1, 0, 1, 0);
		
		//esfera.rotate(-1, 1, 1, 0);
		
		
	}
	
	/**
	 * 
	 */
	@Override
	public void onSurfaceChanged(GL10 unused, int widht, int height) {
		glViewport(0, 0, widht, height);
		MMatrix.setPerspective(projectionMatrix, 45, (float) widht / (float) height, 1f, 100000f);
		//objeto.setProjectionMatrix(projectionMatrix);
		objeto2.setProjectionMatrix(projectionMatrix);
		//esfera.setProjectionMatrix(projectionMatrix);
		monkey.setProjectionMatrix(projectionMatrix);
		
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
		//objeto = new GraysKleynBottle(this);
		//objeto.translate(4, 0, -15f);
		//objeto.setDrawMode(GL_TRIANGLE_STRIP);
		
		//esfera = new Sphere(this);
		//esfera.translate(0, 0, -4f);
		//esfera.setDrawMode(GL_TRIANGLE_STRIP);
		
		monkey = new PhongMonkey(this);
		//monkey = new BlueMonkey(this);
		monkey.translate(0, 0, -4f);
		//monkey.setDrawMode(GL_LINES);
		
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
