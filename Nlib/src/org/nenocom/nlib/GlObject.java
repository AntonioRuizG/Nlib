/**
 * The object-oriented model makes it easy to build up programs
 * by accretion. What this often means, in practice, is that 
 * it provides a structured way to write spaghetti code.
 * — Paul Graham
 *
 */
package org.nenocom.nlib;
import static android.opengl.GLES20.*;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import org.nenocom.nlib.shaders.Shader;

import android.opengl.Matrix;

/**
 * @author Antonio Ruiz
 *
 */
public abstract class GlObject {
	
	private Shader shader;
	
	protected final int uMVPmatrixHandler;
	protected final int uColorHandler;
	protected final int aPositionHandler;
	
	protected int vertexBufferHandler;
	
	protected int program;
	protected int vertexCount;
	
	protected float[] modelMatrix;
	private final float[] MVPmatrix;
	private float[] projectionMatrix;
	private float[] viewMatrix;

	private final float[] color;
	
	private int drawMode = GL_TRIANGLES;

	protected final NlibRenderer renderer;
	
	public GlObject(NlibRenderer renderer) {
		this.renderer = renderer;
		float[] vertices = getVertices();
		vertexCount = vertices.length/3;
		
		vertexBufferHandler = getBufferHandler(vertices);
		
		MVPmatrix = new float[16];
		modelMatrix = new float[16];
		viewMatrix = new float[16];
		projectionMatrix = new float[16];
		
		color = getColor();
		
		Matrix.setIdentityM(modelMatrix, 0);
		Matrix.setIdentityM(MVPmatrix, 0);
		Matrix.setIdentityM(viewMatrix, 0);
		Matrix.setIdentityM(projectionMatrix, 0);
		
		shader = new UniformColorShader();
		program = shader.getShaderProgram();
		glUseProgram(program);
		
		aPositionHandler = glGetAttribLocation(program, "aPosition");
		uMVPmatrixHandler = glGetUniformLocation(program, "MVPmatrix");
		uColorHandler = glGetUniformLocation(program, "uColor");
	}
	
	protected abstract float[] getColor();

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
		
		float[] MVmatrix = new float[16];
		Matrix.multiplyMM(MVmatrix , 0, viewMatrix, 0, modelMatrix, 0);
		Matrix.multiplyMM(MVPmatrix, 0, projectionMatrix, 0, MVmatrix, 0);
		
		
		glUniformMatrix4fv(uMVPmatrixHandler, 1, false, MVPmatrix, 0);
		glUniform4fv(uColorHandler, 1, color, 0);
		
		glDrawArrays(drawMode , 0, vertexCount);
		
		glDisableVertexAttribArray(aPositionHandler);
		
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
	
	
	public void setProjectionMatrix(float[] projectionMatrix) {
		this.projectionMatrix = projectionMatrix;
	}

	public void translate(float x, float y, float z) {
		Matrix.translateM(modelMatrix, 0, x, y, z);		
	}

	public void rotate(float angleInDegrees, float x, float y, float z){
		Matrix.rotateM(modelMatrix, 0, angleInDegrees, x, y, z);
	}
	
	public void scale(float sx, float sy, float sz){
		Matrix.scaleM(modelMatrix, 0, sx, sy, sz);
	}
	
	/**
	 * @param drawMode GLES20 draw mode.
	 */
	public void setDrawMode(int drawMode) {
		this.drawMode = drawMode;
	}
	
	public void destroy(){
		//TODO comprobar referencias
		int[] buffers = new int[]{vertexBufferHandler};
		glDeleteBuffers(buffers.length, buffers, 0);
		shader.destroy();
	}
}
	
	