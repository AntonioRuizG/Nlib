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
public class BluePlasticMaterial extends Material {

	private static float[] ambientColor = new float[]{0.0f, 0.0f, 1.0f, 1.0f};
	private static float[] diffuseColor = new float[]{0.0f, 0.0f, 1.0f, 1.0f};
	private static float[] specularColor = new float[]{1.0f, 1.0f, 1.0f, 1.0f};
	private static float shiness = 160;

	
	public BluePlasticMaterial() {
		super(ambientColor, diffuseColor, specularColor, shiness);
	}

}
