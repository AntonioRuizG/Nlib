/**
 * If debugging is the process of removing bugs, then programming must be the process of putting them in.
 * Edsger W. Dijkstra
 */
package org.nenocom.nlib;

/**
 * @author Antonio Ruiz
 *
 */
public class PerPixelShader extends Shader {

	public PerPixelShader(){
		vSource = 
				"uniform mat4 MVPmatrix;" + "\n" +
				"uniform mat4 MVmatrix;" + "\n" +
				  			
				"attribute vec4 aPosition;" + "\n" +
				"attribute vec4 aColor;" + "\n" +
				"attribute vec3 aNormal;" + "\n" +
						  
				"varying vec3 vPosition;" + "\n" +
				"varying vec4 vColor;" + "\n" +
				"varying vec3 vNormal;" + "\n" +
						  
				"void main()" + "\n" +
				"{" + "\n" +
				"	vPosition = vec3(MVmatrix * aPosition);" + "\n" +
						
				"	vColor = aColor;" + "\n" +
					
				"	//vNormal = normalize(vNormal); " + "\n" +
				"   vNormal = vec3(MVmatrix * vec4(aNormal, 0.0));" + "\n" +
				          
				"	gl_Position = MVPmatrix * aPosition;" + "\n" +
				"}";
	
	
		fSource = 
				"precision mediump float;" + "\n" +
				
				"uniform vec3 uLightPos;" + "\n" +
				
				"varying vec3 vPosition;" + "\n" +
				"varying vec4 vColor;" + "\n" +
				"varying vec3 vNormal;" + "\n" +
				"const float ambient = 0.4;" + "\n" +
				"const vec3 diffuseColor = vec3(0.1, 0.1, 0.1);" + "\n" +
				"const vec3 specColor = vec3(0.5, 0.5, 0.5);" + "\n" +
				
				"void main()" + "\n" +
				"{" + "\n" +
				"	float distance = length(uLightPos - vPosition);" + "\n" +
					
				"	vec3 lightVector = normalize(uLightPos - vPosition);" + "\n" +
					
				"	vec3 reflectDir = reflect(-lightVector, vNormal);" + "\n" +
				
				"	float lambertian = max(dot(lightVector, vNormal), 0.0);" + "\n" +
				
				"	float specular = 0.0;" + "\n" +
				"	if(lambertian > 0.0) {" + "\n" +
				"		float specAngle = max(dot(reflectDir, normalize(-vPosition)), 0.0);" + "\n" +
				"		specular = pow(specAngle, 1.5);" + "\n" +
				"	}" + "\n" +
				"	lambertian = lambertian * (1.0 / (1.0 + (0.80 * distance)));" + "\n" +
				"	specular = specular * (1.0 / (1.0 + (0.80 * distance)));" + "\n" +
				
				"	gl_FragColor = vec4(ambient*vec3(vColor) + lambertian*vec3(vColor) + specular*vec3(vColor), vColor.a);" + "\n" +
				"}";
	}
}
