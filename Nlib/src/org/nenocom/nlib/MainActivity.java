package org.nenocom.nlib;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends Activity {

	private GLSurfaceView surface;
	private boolean rendererEstablecido = false;
	private NlibRenderer renderer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		surface = new GLSurfaceView(this);
		iniciaOpengl();
		setContentView(surface);
		
		
	}
	
	@Override
	public void onPause(){
		super.onPause();
		if(rendererEstablecido){
			surface.onPause();
		}
	}

	@Override 
	protected void onResume() {
		super.onResume();
		if (rendererEstablecido) {
			surface.onResume();
		}
	}
	
	/**
	 * Inicia OpenGL ES 2.0 si el dispositivo lo soporta.
	 */
	private void iniciaOpengl() {
		if (soportaOpenGl2()) { 
			// Establece un contexto compatible con OpenGL ES 2.0. 
			surface.setEGLContextClientVersion(2);
			// Asigna el renderer.
			renderer = new NlibRenderer(this);
			surface.setRenderer(renderer);
			rendererEstablecido = true;
		} else {
			Toast.makeText(this, "This device does not support OpenGL ES 2.0.", Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * 
	 * @return true si el dispositivo soporta OpenGL ES 2.0.
	 */
	private boolean soportaOpenGl2() {
		final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
		return configurationInfo.reqGlEsVersion >= 0x20000;
	}
}
