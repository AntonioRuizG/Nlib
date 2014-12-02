
package org.nenocom.nlib.test;

import org.nenocom.nlib.ColorLightObject;
import org.nenocom.nlib.NlibRenderer;

/**
 * @author Antonio Ruiz
 *
 */
public class Sphere extends ColorLightObject {

	private static final int samplesV=16;
	private static final int samplesU=16;

	/**
	 * @param renderer
	 */
	public Sphere(NlibRenderer renderer) {
		super(renderer);
		
	}

	/* (non-Javadoc)
	 * @see org.nenocom.nlib.ColorLightObject#getNormals(float[])
	 */
	@Override
	protected float[] getNormals(float[] vertices) {
		return getVertices();
	}

	/* (non-Javadoc)
	 * @see org.nenocom.nlib.ColorLightObject#getColors(float[])
	 */
	@Override
	protected float[] getColors(float[] vertices) {
		return new float[vertices.length*4/3];
	}

	/* (non-Javadoc)
	 * @see org.nenocom.nlib.ColorLightObject#getVertices()
	 */
	@Override
	protected float[] getVertices() {
		float[] vertices = new float[samplesV*samplesU*18];
		int offset=0;
		for(int j=0;j<samplesV;j++){
			for(int i=0;i<samplesU;i++){
				
				float v = (float) ((i/(float)samplesV)*Math.PI);
				float u = (float) ((j/(float)samplesU)*2*Math.PI);
				double[] vertice=getVertice(u, v);
				vertices[offset++] = (float) vertice[0];
				vertices[offset++] = (float) vertice[1];
				vertices[offset++] = (float) vertice[2];
				
				v = (float) (((i+1)/(float)samplesV)*Math.PI);
				u = (float) (((j)/(float)samplesU)*2*Math.PI);
				vertice=getVertice(u, v);
				vertices[offset++] = (float) vertice[0];
				vertices[offset++] = (float) vertice[1];
				vertices[offset++] = (float) vertice[2];
				
				v = (float) (((i+1)/(float)samplesV)*Math.PI);
				u = (float) (((j+1)/(float)samplesU)*2*Math.PI);
				vertice=getVertice(u, v);
				vertices[offset++] = (float) vertice[0];
				vertices[offset++] = (float) vertice[1];
				vertices[offset++] = (float) vertice[2];
				
				v = (float) (((i)/(float)samplesV)*Math.PI);
				u = (float) (((j)/(float)samplesU)*2*Math.PI);
				vertice=getVertice(u, v);
				vertices[offset++] = (float) vertice[0];
				vertices[offset++] = (float) vertice[1];
				vertices[offset++] = (float) vertice[2];
				
				v = (float) (((i+1)/(float)samplesV)*Math.PI);
				u = (float) (((j+1)/(float)samplesU)*2*Math.PI);
				vertice=getVertice(u, v);
				vertices[offset++] = (float) vertice[0];
				vertices[offset++] = (float) vertice[1];
				vertices[offset++] = (float) vertice[2];
				
				v = (float) (((i)/(float)samplesV)*Math.PI);
				u = (float) (((j+1)/(float)samplesU)*2*Math.PI);
				vertice=getVertice(u, v);
				vertices[offset++] = (float) vertice[0];
				vertices[offset++] = (float) vertice[1];
				vertices[offset++] = (float) vertice[2];
				
				
			}
		}
		return vertices;
	}
	
	private double[] getVertice(double u, double v){
		double[] vertice = new double[3];
		double cosu=Math.cos(u);
		double sinu=Math.sin(u);
		double sinv=Math.sin(v);
		double cosv=Math.cos(v);
		
		vertice[0] = cosu*sinv;
		vertice[1] = sinu*sinv;
		vertice[2] = cosv;
		
		return vertice;
	}
	

}
