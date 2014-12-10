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
public class CeramicMaterial extends Material {

	private static float[] specularColor = new float[]{1.0f, 1.0f, 1.0f, 1.0f};
	private static float shiness = 1000;

	
	public CeramicMaterial(float[] color) {
		super(color, color, specularColor, shiness);
	}

}
