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
					
					
				"    vNormal = vec3(MVmatrix * vec4(aNormal, 0.0));" + "\n" +
				          
				"	gl_Position = MVPmatrix * aPosition;" + "\n" +
				"}";
	
	
		fSource = 
				"precision mediump float;" + "\n" +
				
				"uniform vec3 uLightPos;" + "\n" +
				
				"varying vec3 vPosition;" + "\n" +
				"varying vec4 vColor;" + "\n" +
				"varying vec3 vNormal;" + "\n" +
				
				"void main()" + "\n" +
				"{" + "\n" +
				"	float distance = length(uLightPos - vPosition);" + "\n" +
					
				"	vec3 lightVector = normalize(uLightPos - vPosition);" + "\n" +
					
				"	float diffuse = max(dot(vNormal, lightVector), 0.0);" + "\n" +
					
				"	diffuse = diffuse * (1.0 / (1.0 + (0.80 * distance)));" + "\n" +
					
				"	diffuse = diffuse + 0.6;" + "\n" +
					
				"	gl_FragColor = (vColor * diffuse);" + "\n" +
				"}";
	}
}
