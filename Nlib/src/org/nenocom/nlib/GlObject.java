/**
 * If debugging is the process of removing bugs, then programming must be the process of putting them in.
 * Edsger W. Dijkstra
 */
package org.nenocom.nlib;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import android.opengl.Matrix;
import static android.opengl.GLES20.*;

/**
 * @author Antonio Ruiz
 *
 */
public abstract class GlObject {
	
	private Shader shader;
	
	private int uMVPmatrixHandler;
	private int uMVmatrixHandler;
	private int uLightPositionHandler;

	private int aPositionHandler;
	private int aColorHandler;
	private int aNormalHandler;
	
	//low-level buffer pointers, be carefull, could get memory leak
	private int vertexBufferHandler;
	private int colorBufferHandler;
	private int normalBufferHandler;
	
	private int program;
	private int vertexCount;
	protected float[] modelMatrix;

	private float[] lightPos;

	private final float[] MVPmatrix;

	private float[] MVmatrix;

	private float[] projectionMatrix;

	private float[] viewMatrix;

	private int drawMode = GL_TRIANGLES;
	
	
	
	
	public GlObject(NlibRenderer renderer) {
		float[] vertices = getVertices();
		float[] colors = getColors();
		float[] normals = getNormals();
		vertexCount = vertices.length/3;
		//TODO test if colors and normals.size == vertices.size Im lazy
		
		
		vertexBufferHandler = getBufferHandler(vertices);
		colorBufferHandler = getBufferHandler(colors);
		normalBufferHandler = getBufferHandler(normals);
		
		//TODO make this a list accesible from outside the class
		lightPos = new float[]{0, 0, 0};
		
		MVPmatrix = new float[16];
		MVmatrix = new float[16];
		modelMatrix = new float[16];
		viewMatrix = new float[16];
		projectionMatrix = new float[16];
		Matrix.setIdentityM(modelMatrix, 0);
		Matrix.setIdentityM(MVPmatrix, 0);
		Matrix.setIdentityM(MVmatrix, 0);
		Matrix.setIdentityM(viewMatrix, 0);
		Matrix.setIdentityM(projectionMatrix, 0);
		
		shader = new PerPixelShader();
		program = shader.getShaderProgram();
		glUseProgram(program);
		
		aPositionHandler = glGetAttribLocation(program, "aPosition");
		aColorHandler = glGetAttribLocation(program, "aColor");
		aNormalHandler = glGetAttribLocation(program, "aNormal");
		
		uMVPmatrixHandler = glGetUniformLocation(program, "MVPmatrix");
		uMVmatrixHandler = glGetUniformLocation(program, "MVmatrix");
		uLightPositionHandler = glGetUniformLocation(program, "uLightPos");
		
		
	}

	protected abstract float[] getNormals();

	protected abstract float[] getColors();

	protected abstract float[] getVertices();
	
	/**
	 * 
	 * @param vertices
	 * @return low-level buffer pointer, don't lose this, could get memory leak
	 */
	private int getBufferHandler(float[]vertices) {
		int bufferHandler;
		final FloatBuffer vertexData = ByteBuffer
				.allocateDirect(vertices.length * 4)
				.order(ByteOrder.nativeOrder())
				.asFloatBuffer();
		vertexData.put(vertices);
		vertexData.position(0);
		int[] bufferHandlers = new int[1];
		glGenBuffers(1, bufferHandlers, 0);
		bufferHandler = bufferHandlers[0];
		glBindBuffer(GL_ARRAY_BUFFER, bufferHandler);
		glBufferData(GL_ARRAY_BUFFER, vertexData.capacity() * 4,
			    vertexData, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		vertexData.clear();
		return bufferHandler;
	}

	public void onDrawFrame() {
		glUseProgram(program);
		
		glBindBuffer(GL_ARRAY_BUFFER, vertexBufferHandler);
		glEnableVertexAttribArray(aPositionHandler);
		glVertexAttribPointer(aPositionHandler, 3, GL_FLOAT, false, 0, 0);
		
		glBindBuffer(GL_ARRAY_BUFFER, colorBufferHandler);
		glEnableVertexAttribArray(aColorHandler);
		glVertexAttribPointer(aColorHandler, 4, GL_FLOAT, false, 0, 0);
		
		glBindBuffer(GL_ARRAY_BUFFER, normalBufferHandler);
		glEnableVertexAttribArray(aNormalHandler);
		glVertexAttribPointer(aNormalHandler, 3, GL_FLOAT, false, 0, 0);
		
		
		Matrix.multiplyMM(MVmatrix, 0, viewMatrix, 0, modelMatrix, 0);
		Matrix.multiplyMM(MVPmatrix, 0, projectionMatrix, 0, MVmatrix, 0);
		
		
		glUniformMatrix4fv(uMVPmatrixHandler, 1, false, MVPmatrix, 0);
		glUniformMatrix4fv(uMVmatrixHandler, 1, false, MVmatrix, 0);
		glUniform3f(uLightPositionHandler, lightPos[0], lightPos[1], lightPos[2]);
		
		glDrawArrays(drawMode , 0, vertexCount);
		
		glDisableVertexAttribArray(aPositionHandler);
		glDisableVertexAttribArray(aColorHandler);
		glDisableVertexAttribArray(aNormalHandler);
		
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
	
	
	public void setProjectionMatrix(float[] projectionMatrix) {
		this.projectionMatrix = projectionMatrix;
	}

	public void translate(float f, float g, float h) {
		Matrix.translateM(modelMatrix, 0, f, g, h);		
	}

	/**
	 * @param glLines
	 */
	public void setDrawMode(int drawMode) {
		this.drawMode = drawMode;
	}
	
	//TODO destroy method
	
	//TODO rotate and scale methods
}
