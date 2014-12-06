/**
 * The object-oriented model makes it easy to build up programs
 * by accretion. What this often means, in practice, is that 
 * it provides a structured way to write spaghetti code.
 * — Paul Graham
 *
 */
package org.nenocom.nlib;

import org.nenocom.nlib.shaders.Shader;

/**
 * @author Antonio Ruiz
 *
 */
public class UniformColorShader extends Shader {
	public UniformColorShader(){
		vSource = 
				"uniform mat4 MVPmatrix;" + "\n" +
				  			
				"attribute vec4 aPosition;" + "\n" +
						  
				"void main()" + "\n" +
				"{" + "\n" +
				"	gl_Position = MVPmatrix * aPosition;" + "\n" +
				"}";
	
	
		fSource = 
				"precision mediump float;" + "\n" +
		
				"uniform vec4 uColor;" + "\n" +
				
				"void main()" + "\n" +
				"{" + "\n" +
				"	gl_FragColor = uColor;" + "\n" +
				"}";
	}
}