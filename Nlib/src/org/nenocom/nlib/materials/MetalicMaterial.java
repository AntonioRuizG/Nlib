/**
 * Science is a differential equation.
 * Religion is a boundary condition.
 * Alan Turing
*/
package org.nenocom.nlib.materials;


/**
 * @author Antonio Ruiz
 *
 */
public class MetalicMaterial extends Material {
	
	private static float shiness = 3;

	
	public MetalicMaterial(float[] color) {
		super(color, color, color, shiness);
	}

}
