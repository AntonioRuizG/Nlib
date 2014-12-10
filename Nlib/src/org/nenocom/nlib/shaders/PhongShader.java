/**
 * If debugging is the process of removing bugs, then programming must be the process of putting them in.
 * Edsger W. Dijkstra
 */
package org.nenocom.nlib.shaders;


/**
 * @author Antonio Ruiz
 *
 */
public class PhongShader extends Shader {
	
	

	public PhongShader(Material material){
		super(material);
	}
	
	@Override
	protected String getVertexShaderSource() {
		return  
				"attribute vec4 aPosition;" + "\n" +
				"attribute vec3 aNormal;" + "\n" +
				  			
				"varying vec4 vPosition;" + "\n" +
				"varying vec3 vNormalDirection;" + "\n" +
				
				"uniform mat4 MVPmatrix;" + "\n" +
				"uniform mat4 modelMatrix;" + "\n" +
				"uniform mat3 normalMatrix;" + "\n" +
						  
				"void main()" + "\n" +
				"{" + "\n" +
				"	vPosition = modelMatrix * aPosition;" + "\n" +
						
				"	vNormalDirection = normalize(normalMatrix * aNormal);" + "\n" +
				          
				"	gl_Position = MVPmatrix * aPosition;" + "\n" +
				"}";
	}
	
	@Override
	protected String getFragmentShaderSource() {
		return  
				"precision mediump float;" + "\n" +
				
				"varying vec4 vPosition;  " + "\n" +
				"varying vec3 vNormalDirection;  " + "\n" +

				"uniform mat4 v_inv;" + "\n" +
 
				"struct lightSource" + "\n" +
				"{" + "\n" +
				"  vec4 position;" + "\n" +
				"  vec4 diffuse;" + "\n" +
				"  vec4 specular;" + "\n" +
				"  float constantAttenuation, linearAttenuation, quadraticAttenuation;" + "\n" +
				"  float spotCutoff, spotExponent;" + "\n" +
				"  vec3 spotDirection;" + "\n" +
				"};" + "\n" +
				"lightSource light0 = lightSource(" + "\n" +
				"  vec4(0.0,  1.0,  2.0, 1.0)," + "\n" +
				"  vec4(1.0,  1.0,  1.0, 1.0)," + "\n" +
				"  vec4(1.0,  1.0,  1.0, 1.0)," + "\n" +
				"  0.0, 1.0, 0.0," + "\n" +
				"  180.0, 0.0," + "\n" +
				"  vec3(0.0, 0.0, 0.0)" + "\n" +
				");" + "\n" +
				"vec4 scene_ambient = vec4(0.4, 0.4, 0.4, 1.0);" + "\n" +
				
				"struct material" + "\n" +
				"{" + "\n" +
				"  vec4 ambient;" + "\n" +
				"  vec4 diffuse;" + "\n" +
				"  vec4 specular;" + "\n" +
				"  float shininess;" + "\n" +
				"};" + "\n" +
				"material frontMaterial = "+material.toString()+";" + "\n" +
 
				"void main()" + "\n" +
				"{" + "\n" +
				"  vec3 normalDirection = normalize(vNormalDirection);" + "\n" +
				"  vec3 viewDirection = normalize(vec3(v_inv * vec4(0.0, 0.0, 0.0, 1.0) - vPosition));" + "\n" +
				"  vec3 lightDirection;" + "\n" +
				"  float attenuation;" + "\n" +
 
				"  if (0.0 == light0.position.w) // directional light?" + "\n" +
				"    {" + "\n" +
				"      attenuation = 1.0; // no attenuation" + "\n" +
				"      lightDirection = normalize(vec3(light0.position));" + "\n" +
				"    }" + "\n" +
				"  else // point light or spotlight (or other kind of light)" + "\n" + 
				"    {" + "\n" +
				"      vec3 positionToLightSource = vec3(light0.position - vPosition);" + "\n" +
				"      float distance = length(positionToLightSource);" + "\n" +
				"      lightDirection = normalize(positionToLightSource);" + "\n" +
				"      attenuation = 1.0 / (light0.constantAttenuation" + "\n" +
				"                           + light0.linearAttenuation * distance" + "\n" +
				"                           + light0.quadraticAttenuation * distance * distance);" + "\n" +
 
				"      if (light0.spotCutoff <= 90.0) // spotlight?" + "\n" +
				"	{" + "\n" +
				"	  float clampedCosine = max(0.0, dot(-lightDirection, light0.spotDirection));" + "\n" +
				"	  if (clampedCosine < cos(radians(light0.spotCutoff))) // outside of spotlight cone?" + "\n" +
				"	    {" + "\n" +
				"	      attenuation = 0.0;" + "\n" +
				"	    }" + "\n" +
				"	  else" + "\n" +
				"	    {" + "\n" +
				"	      attenuation = attenuation * pow(clampedCosine, light0.spotExponent);" + "\n" +   
				"	    }" + "\n" +
				"	}" + "\n" +
				"    }" + "\n" +
 
				"  vec3 ambientLighting = vec3(scene_ambient) * vec3(frontMaterial.ambient);" + "\n" +
 
				"  vec3 diffuseReflection = attenuation " + "\n" +
				"    * vec3(light0.diffuse) * vec3(frontMaterial.diffuse)" + "\n" +
				"    * max(0.0, dot(normalDirection, lightDirection));" + "\n" +
 
				"  vec3 specularReflection;" + "\n" +
				"  if (dot(normalDirection, lightDirection) < 0.0) // light source on the wrong side?" + "\n" +
				"    {" + "\n" +
				"      specularReflection = vec3(0.0, 0.0, 0.0); // no specular reflection" + "\n" +
				"    }" + "\n" +
				"  else // light source on the right side" + "\n" +
				"    {" + "\n" +
				"      specularReflection = attenuation * vec3(light0.specular) * vec3(frontMaterial.specular)" + "\n" + 
				"	* pow(max(0.0, dot(reflect(-lightDirection, normalDirection), viewDirection)), frontMaterial.shininess);" + "\n" +
				"    }" + "\n" +
 
				"  gl_FragColor = vec4(ambientLighting + diffuseReflection + specularReflection, 1.0);" + "\n" +
				"}";
	}

	
}
