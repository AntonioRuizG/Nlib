/**
 * If debugging is the process of removing bugs, then programming must be the process of putting them in.
 * Edsger W. Dijkstra
 */
package org.nenocom.nlib;

import static android.opengl.GLES20.*;
import android.util.Log;

/**
 * @author Antonio Ruiz
 *
 */
public abstract class Shader {
	
	protected String vSource;
	protected String fSource;
	
	private int shaderProgram = 0;
	private boolean changed = true;
	
	public int getShaderProgram(){
		if(changed){
			changed = false;
			shaderProgram = buildProgram(vSource, fSource);
		}
		return shaderProgram;
	}
	
	private static int compileShader(int type, String shaderCode) {

		int shader = glCreateShader(type);

		if (shader == 0) {
			Log.e("Shader", "Error creating new shader.");
			return 0;
		}
		glShaderSource(shader, shaderCode);
		glCompileShader(shader);

		final int[] status = new int[1];
		glGetShaderiv(shader, GL_COMPILE_STATUS, status, 0);

		
		if (status[0] == 0) {
			glDeleteShader(shader);
			Log.e("Shader", "Shader compilation failed.");
			return 0;
		}
		return shader;
	}
	
	public static int buildProgram(String vsSource, String fsSource) {
		int program = glCreateProgram();
		int vertexShader = compileShader(GL_VERTEX_SHADER, vsSource);
		int fragmentShader = compileShader(GL_FRAGMENT_SHADER, fsSource);
		
		if (program == 0) {
			Log.e("Shader", "Could not create new program");
			return 0;
		}

		glAttachShader(program, vertexShader);
		glAttachShader(program, fragmentShader);
		glLinkProgram(program);

		final int[] linkStatus = new int[1];
		glGetProgramiv(program, GL_LINK_STATUS, linkStatus, 0);

		if (linkStatus[0] == 0) {
			glDeleteProgram(program);
			Log.e("Shader", "Linking of program failed.");
			return 0;
		}

		return program;
	}

	/**
	 * 
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
