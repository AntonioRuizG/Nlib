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
public class GraysKleynBottle extends GlObject {
	static int samplesV=50;
	static int samplesU=50;
	
	private float rx = (float)Math.random()*2-1;
	private float ry = (float)Math.random()*2-1;
	private float rz = (float)Math.random()*2-1;
	
	public GraysKleynBottle(NlibRenderer renderer) {
		super(renderer);
	}

	@Override
	protected float[] getNormals() {
		return getVertices();
	}

	@Override
	protected float[] getColors() {
		float[] colores = new float[samplesV*samplesU*16];
		int offset=0;
		for(int j=0;j<samplesV;j++){
			for(int i=0;i<samplesU;i++){
				
				float u = (float) (((j*3)/(float)(samplesU))*2*Math.PI);
				
				double[] color=getColor(u);
				for(int k=0;k<4;k++){
					colores[offset++] = (float) color[0];
					colores[offset++] = (float) color[1];
					colores[offset++] = (float) color[2];
					colores[offset++] = (float) color[3];
				}
			}
		}
		return colores;
	}
	
	private double[] getColor(float u) {
		double[] color = new double[4];
		
		color[0] = (Math.cos(u)+1.0)/2.0;
		color[1] = (Math.sin(u)+1.0)/2.0;
		color[2] = (Math.cos(u*2)+1.0)/2.0;
		color[3] = 0.5f;
		return color;
	}

	public void onDrawFrame(){
		super.onDrawFrame();
		Matrix.rotateM(modelMatrix, 0, 1, rx, ry, rz);
		
		
	}
	
	protected float[] getVertices() {
		
		float[] vertices = new float[samplesV*samplesU*12];
		int offset=0;
		for(int j=0;j<samplesV;j++){
			for(int i=0;i<samplesU;i++){
				
				float v = (float) ((i/(float)samplesV)*2*Math.PI);
				float u = (float) ((j/(float)samplesU)*4*Math.PI);
				
				double[] vertice=getVertice(u, v);
				vertices[offset++] = (float) vertice[0];
				vertices[offset++] = (float) vertice[1];
				vertices[offset++] = (float) vertice[2];
				
				v = (float) (((i+1)/(float)samplesV)*2*Math.PI);
				u = (float) (((j)/(float)samplesU)*4*Math.PI);
				vertice=getVertice(u, v);
				vertices[offset++] = (float) vertice[0];
				vertices[offset++] = (float) vertice[1];
				vertices[offset++] = (float) vertice[2];
				
				v = (float) ((i/(float)samplesV)*2*Math.PI);
				u = (float) (((j+1)/(float)samplesU)*4*Math.PI);
				vertice=getVertice(u, v);
				vertices[offset++] = (float) vertice[0];
				vertices[offset++] = (float) vertice[1];
				vertices[offset++] = (float) vertice[2];
				
				v = (float) (((i+1)/(float)samplesV)*2*Math.PI);
				u = (float) (((j+1)/(float)samplesU)*4*Math.PI);
				vertice=getVertice(u, v);
				vertices[offset++] = (float) vertice[0];
				vertices[offset++] = (float) vertice[1];
				vertices[offset++] = (float) vertice[2];
			}
		}
		return vertices;
	}
	
	
	public double[] getVertice(double u, double v){
		double[] punto = new double[3];
		//0 <= u <= 4 pi
		//0 <= v <= 2 pi 
		
		double a=3.0;
		double n=2.0;
		double m=1.0;
		
		punto[0] = (a + Math.cos(n*u/2.0) * Math.sin(v) - Math.sin(n*u/2.0) * Math.sin(2*v)) * Math.cos(m*u/2.0);
		punto[1] = (a + Math.cos(n*u/2.0) * Math.sin(v) - Math.sin(n*u/2.0) * Math.sin(2*v)) * Math.sin(m*u/2.0);
		punto[2] = Math.sin(n*u/2.0) * Math.sin(v) + Math.cos(n*u/2.0) * Math.sin(2*v);
		
		
		return punto;
	}
	
}
