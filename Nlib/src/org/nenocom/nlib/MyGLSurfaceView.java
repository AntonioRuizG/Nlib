/**
 * If debugging is the process of removing bugs, then programming must be the process of putting them in.
 * Edsger W. Dijkstra
 */
package org.nenocom.nlib;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

/**
 * @author Antonio Ruiz
 *
 */
public class MyGLSurfaceView extends GLSurfaceView {

	/**
	 * @param context
	 */
	public MyGLSurfaceView(Context context) {
		super(context);
	}

	/* (non-Javadoc)
	 * @see android.opengl.GLSurfaceView#setRenderer(android.opengl.GLSurfaceView.Renderer)
	 */
	@Override
	public void setRenderer(Renderer renderer) {
		// TODO Auto-generated method stub
		super.setRenderer(renderer);
	}

	/* (non-Javadoc)
	 * @see android.view.View#onTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_MOVE){
			
		}
		return super.onTouchEvent(event);
	}
	
	

}
