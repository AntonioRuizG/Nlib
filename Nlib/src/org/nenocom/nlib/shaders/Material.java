/**
 * Science is a differential equation.
 * Religion is a boundary condition.
 * Alan Turing
*/
package org.nenocom.nlib.shaders;

/**
 * @author Antonio Ruiz
 *
 */
public class Material {

	private float[] ambientColor;
	private float[] diffuseColor;
	private float[] specularColor;
	private float shiness = (float)Math.random()*100;
	
	public Material(){
		ambientColor = new float[]{(float)Math.random(),
				(float)Math.random(),(float)Math.random(),
				(float)Math.random()};
		
		diffuseColor = new float[]{(float)Math.random(),
				(float)Math.random(),(float)Math.random(),
				(float)Math.random()};
		
		specularColor = new float[]{(float)Math.random(),
				(float)Math.random(),(float)Math.random(),
				(float)Math.random()};
	}

	/**
	 * @return
	 */
	public String getAmbientComponent() {
		
		return "vec4("+ambientColor[0]+", "+ambientColor[1]+", "+ambientColor[2]+", 1.0)";
	}

	/**
	 * @return
	 */
	public String getDiffuseComponent() {
		return "vec4("+diffuseColor[0]+", "+diffuseColor[1]+", "+diffuseColor[2]+", 1.0)";
	}

	/**
	 * @return
	 */
	public String getSpecularComponent() {
		return "vec4("+specularColor[0]+", "+specularColor[1]+", "+specularColor[2]+", 1.0)";
	}

	/**
	 * @return
	 */
	public float getShiness() {
		return shiness ;
	}

}
