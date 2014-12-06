
package org.nenocom.nlib.test;

import org.nenocom.nlib.ColorLightObject;
import org.nenocom.nlib.NlibRenderer;
import org.nenocom.nlib.geometry.ObjectBuilder;

/**
 * @author Antonio Ruiz
 *
 */
public class Sphere extends ColorLightObject {

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
		float[] colores =  new float[vertices.length*4/3];
		int offset = 0;
		for(int i=0;i<vertices.length/3;i++){
			colores[offset++] = vertices[offset-i-1];
			colores[offset++] = vertices[offset-i-1];
			colores[offset++] = vertices[offset-i-1];
			colores[offset++] = 1;
		}
		return colores;
	}

	/* (non-Javadoc)
	 * @see org.nenocom.nlib.ColorLightObject#getVertices()
	 */
	@Override
	protected float[] getVertices() {
		return ObjectBuilder.getIcosphere(2);
	}
}
