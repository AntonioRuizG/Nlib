/**
 * If debugging is the process of removing bugs, then programming must be the process of putting them in.
 * Edsger W. Dijkstra
 */
package org.nenocom.nlib;

import android.opengl.Matrix;

/**
 * @author Antonio Ruiz
 *
 */
public class KleinBottle extends GlObject {
	static int samplesV=50;
	static int samplesU=50;
	
	private float rx = 0.1f;
	private float ry = 1;
	private float rz = 0;
	
	public KleinBottle(NlibRenderer renderer) {
		super(renderer);
	}

	@Override
	protected float[] getNormals() {
		return getVertices();
	}

	@Override
	protected float[] getColors() {
		float[] colores = new float[samplesV*samplesU*24];
		int offset=0;
		for(int j=0;j<samplesV;j++){
			for(int i=0;i<samplesU;i++){
				
				
				
				
				float u = (float) ((j/(float)samplesU)*2*Math.PI);
				double[] vertice=getColor(u);
				colores[offset++] = (float) vertice[0];
				colores[offset++] = (float) vertice[1];
				colores[offset++] = (float) vertice[2];
				colores[offset++] = (float) vertice[3];
				
				u = (float) (((j)/(float)samplesU)*2*Math.PI);
				vertice=getColor(u);
				colores[offset++] = (float) vertice[0];
				colores[offset++] = (float) vertice[1];
				colores[offset++] = (float) vertice[2];
				colores[offset++] = (float) vertice[3];
				
				u = (float) (((j+1)/(float)samplesU)*2*Math.PI);
				vertice=getColor(u);
				colores[offset++] = (float) vertice[0];
				colores[offset++] = (float) vertice[1];
				colores[offset++] = (float) vertice[2];
				colores[offset++] = (float) vertice[3];
				
				u = (float) (((j)/(float)samplesU)*2*Math.PI);
				vertice=getColor(u);
				colores[offset++] = (float) vertice[0];
				colores[offset++] = (float) vertice[1];
				colores[offset++] = (float) vertice[2];
				colores[offset++] = (float) vertice[3];
				
				u = (float) (((j+1)/(float)samplesU)*2*Math.PI);
				vertice=getColor(u);
				colores[offset++] = (float) vertice[0];
				colores[offset++] = (float) vertice[1];
				colores[offset++] = (float) vertice[2];
				colores[offset++] = (float) vertice[3];
				
				u = (float) (((j+1)/(float)samplesU)*2*Math.PI);
				vertice=getColor(u);
				colores[offset++] = (float) vertice[0];
				colores[offset++] = (float) vertice[1];
				colores[offset++] = (float) vertice[2];
				colores[offset++] = (float) vertice[3];
				
			}
		}
		return colores;
	}
	
	private double[] getColor(float u) {
		double[] color = new double[4];
		
		color[0] = (Math.cos(u)+1.0)/2.0;
		color[1] = (Math.sin(u)+1.0)/2.0;
		color[2] = (Math.cos(2*u+1)+1.0)/2.0;
		color[3] = 0.6f;
		return color;
	}

	public void onDrawFrame(){
		super.onDrawFrame();
		Matrix.rotateM(modelMatrix, 0, -1, rx, ry, rz);
		
		
	}
	
	protected float[] getVertices() {
		
		float[] vertices = new float[samplesV*samplesU*18];
		int offset=0;
		for(int j=0;j<samplesV;j++){
			for(int i=0;i<samplesU;i++){
				
				float v = (float) ((i/(float)samplesV)*2*Math.PI);
				float u = (float) ((j/(float)samplesU)*2*Math.PI);
				double[] vertice=getVertice(u, v);
				vertices[offset++] = (float) vertice[0];
				vertices[offset++] = (float) vertice[1];
				vertices[offset++] = (float) vertice[2];
				
				v = (float) (((i+1)/(float)samplesV)*2*Math.PI);
				u = (float) (((j)/(float)samplesU)*2*Math.PI);
				vertice=getVertice(u, v);
				vertices[offset++] = (float) vertice[0];
				vertices[offset++] = (float) vertice[1];
				vertices[offset++] = (float) vertice[2];
				
				v = (float) (((i+1)/(float)samplesV)*2*Math.PI);
				u = (float) (((j+1)/(float)samplesU)*2*Math.PI);
				vertice=getVertice(u, v);
				vertices[offset++] = (float) vertice[0];
				vertices[offset++] = (float) vertice[1];
				vertices[offset++] = (float) vertice[2];
				
				v = (float) (((i)/(float)samplesV)*2*Math.PI);
				u = (float) (((j)/(float)samplesU)*2*Math.PI);
				vertice=getVertice(u, v);
				vertices[offset++] = (float) vertice[0];
				vertices[offset++] = (float) vertice[1];
				vertices[offset++] = (float) vertice[2];
				
				v = (float) (((i+1)/(float)samplesV)*2*Math.PI);
				u = (float) (((j+1)/(float)samplesU)*2*Math.PI);
				vertice=getVertice(u, v);
				vertices[offset++] = (float) vertice[0];
				vertices[offset++] = (float) vertice[1];
				vertices[offset++] = (float) vertice[2];
				
				v = (float) (((i)/(float)samplesV)*2*Math.PI);
				u = (float) (((j+1)/(float)samplesU)*2*Math.PI);
				vertice=getVertice(u, v);
				vertices[offset++] = (float) vertice[0];
				vertices[offset++] = (float) vertice[1];
				vertices[offset++] = (float) vertice[2];
				
				
			}
		}
		return vertices;
	}

	private double[] getVertice(float u, float v) {
		double cosu=Math.cos(u);
		double sinu=Math.sin(u);
		double sinv=Math.sin(v);
		double cosv=Math.cos(v);
		
		double[] punto = new double[3];
		double r = 4.0*(1 - cosu/2.0);
		 
		 if (!((0 <= u) && (u < Math.PI))){
				punto[0] = (6.0*cosu*(1.0 + sinu) + r*Math.cos(v + Math.PI));
		        punto[1] = (16.0 * sinu);
		 }else{
			 punto[0] = (6.0*cosu*(1.0 + sinu) + r*cosu*cosv);
			 punto[1] = (16.0*sinu + r*sinu*cosv);
		 }
		 punto[2] = (r * sinv);
		 return punto;
	}
	
	

	
}
