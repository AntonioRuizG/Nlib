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
public class Material {

	private final float[] ambientColor;
	private final float[] diffuseColor;
	private final float[] specularColor;
	private float shiness;
	
	public Material(float[] ambientColor, float[] diffuseColor, float[] specularColor, float shiness){
		this.ambientColor = ambientColor;
		this.diffuseColor = diffuseColor;
		this.specularColor = specularColor;
		this.shiness = shiness;
	}

	/**
	 * 
	 */
	public Material() {
		this.ambientColor = new float[]{1.0f, 1.0f, 1.0f, 1.0f};
		this.diffuseColor = new float[]{1.0f, 1.0f, 1.0f, 1.0f};
		this.specularColor = new float[]{1.0f, 1.0f, 1.0f, 1.0f};
		this.shiness = 5f;
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

	@Override
	public String toString(){
		return "material(" + "\n" +
				"  "+getAmbientComponent()+"," + "\n" +
				"  "+getDiffuseComponent()+"," + "\n" +
				"  "+getSpecularComponent()+"," + "\n" +
				"  "+getShiness() + "\n" +
				")";
	}
}
