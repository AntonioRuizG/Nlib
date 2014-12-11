/**
 * The object-oriented model makes it easy to build up programs
 * by accretion. What this often means, in practice, is that 
 * it provides a structured way to write spaghetti code.
 * — Paul Graham
 *
 */
package org.nenocom.nlib.objects;

import org.nenocom.nlib.NlibRenderer;
import org.nenocom.nlib.materials.Material;
import org.nenocom.nlib.utils.ObjLoader;

/**
 * @author Antonio Ruiz
 *
 */
public class PhongMonkey extends PhongObject{



	/**
	 * @param renderer
	 * @param material 
	 */
	public PhongMonkey(NlibRenderer renderer, Material material) {
		super(renderer, material);
	}

	/* (non-Javadoc)
	 * @see org.nenocom.nlib.GlObject#getVertices()
	 */
	@Override
	protected float[] getVertices() {
		return ObjLoader.LoadVertices("monkey");
	}

	/* (non-Javadoc)
	 * @see org.nenocom.nlib.PhongObject#getNormals()
	 */
	@Override
	protected float[] getNormals() {
		return ObjLoader.LoadNormals("monkey");
	}

}
