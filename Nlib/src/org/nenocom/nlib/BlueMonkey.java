/**
 * The object-oriented model makes it easy to build up programs
 * by accretion. What this often means, in practice, is that 
 * it provides a structured way to write spaghetti code.
 * — Paul Graham
 *
 */
package org.nenocom.nlib;

/**
 * @author Antonio Ruiz
 *
 */
public class BlueMonkey extends GlObject{

	/**
	 * @param renderer
	 */
	public BlueMonkey(NlibRenderer renderer) {
		super(renderer);
	}



	/* (non-Javadoc)
	 * @see org.nenocom.nlib.GlObject#getColor()
	 */
	@Override
	protected float[] getColor() {
		return new float[]{0.0f, 0.0f, 1.0f, 1.0f};
	}

	/* (non-Javadoc)
	 * @see org.nenocom.nlib.GlObject#getVertices()
	 */
	@Override
	protected float[] getVertices() {
		return ObjLoader.LoadVertices("monkey");
	}

}
