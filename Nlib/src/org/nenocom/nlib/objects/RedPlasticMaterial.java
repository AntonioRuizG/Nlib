/**
 * Science is a differential equation.
 * Religion is a boundary condition.
 * Alan Turing
*/
package org.nenocom.nlib.objects;

import org.nenocom.nlib.shaders.Material;

/**
 * @author Antonio Ruiz
 *
 */
public class RedPlasticMaterial extends Material {

	private static float[] ambientColor = new float[]{1.0f, 0.0f, 0.0f, 1.0f};
	private static float[] diffuseColor = new float[]{1.0f, 0.0f, 0.0f, 1.0f};
	private static float[] specularColor = new float[]{1.0f, 1.0f, 1.0f, 1.0f};
	private static float shiness = 160;

	
	public RedPlasticMaterial() {
		super(ambientColor, diffuseColor, specularColor, shiness);
	}

}
