/**
 * The object-oriented model makes it easy to build up programs
 * by accretion. What this often means, in practice, is that 
 * it provides a structured way to write spaghetti code.
 * — Paul Graham
 *
 */
package org.nenocom.nlib.objects;

import static android.opengl.GLES20.*;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import org.nenocom.nlib.NlibRenderer;
import org.nenocom.nlib.shaders.PhongShader;
import org.nenocom.nlib.utils.Mat3x3;

import android.opengl.Matrix;

/**
 * @author Antonio Ruiz
 *
 */
public abstract class PhongObject {
	
	public int aPositionHandler;
	public int aNormalHandler;
	
	public int uModelMatrixHandler;
	public int uViewMatrixHandler;
	public int uProjectionMatrixHandler;
	public int uModelInvMatrixHandler;
	public int uViewInvMatrixHandler;
	
	
	
	
	
	
	
	
	protected final int vertexBufferHandler;
	protected final int normalBufferHandler;
	
	protected final int program;
	protected final int vertexCount;
	
	protected final float[] modelMatrix;
	private final float[] viewMatrix;
	private final float[] projectionMatrix;
	//private final float[] modelInvMatrix;

	protected final NlibRenderer renderer;
	private PhongShader shader;
	
	public PhongObject(NlibRenderer renderer) {
		this.renderer = renderer;
		float[] vertices = getVertices();
		float[] normals = getNormals();
		vertexCount = vertices.length/3;
		
		vertexBufferHandler = getBufferHandler(vertices);
		normalBufferHandler = getBufferHandler(normals);
		
		viewMatrix = new float[16];
		modelMatrix = new float[16];
		//modelInvMatrix = new float[9];
		projectionMatrix = new float[16];
		
		Matrix.setIdentityM(modelMatrix, 0);
		
		Matrix.setIdentityM(viewMatrix, 0);
		Matrix.setIdentityM(projectionMatrix, 0);
		
		shader = new PhongShader();
		program = shader.getShaderProgram();
		glUseProgram(program);
		
		aPositionHandler = glGetAttribLocation(program, "v_coord");
		aNormalHandler = glGetAttribLocation(program, "v_normal");
		
		uModelMatrixHandler = glGetUniformLocation(program, "m");
		uViewMatrixHandler = glGetUniformLocation(program, "v");
		uProjectionMatrixHandler = glGetUniformLocation(program, "p");
		uModelInvMatrixHandler = glGetUniformLocation(program, "m_3x3_inv_transp");
		uViewInvMatrixHandler = glGetUniformLocation(program, "v_inv");
		
		
	}
	
	/**
	 * @return
	 */
	protected abstract float[] getNormals();

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
		
		glBindBuffer(GL_ARRAY_BUFFER, normalBufferHandler);
		glEnableVertexAttribArray(aNormalHandler);
		glVertexAttribPointer(aNormalHandler, 3, GL_FLOAT, false, 0, 0);
		
		
		
		glUniformMatrix4fv(uModelMatrixHandler, 1, false, modelMatrix, 0);
		glUniformMatrix4fv(uViewMatrixHandler, 1, false, viewMatrix, 0);
		glUniformMatrix4fv(uProjectionMatrixHandler, 1, false, projectionMatrix, 0);
		
		
		float []temp = new float[18];
	    float[] mMVMatrix = new float[16];
	    Matrix.multiplyMM(mMVMatrix, 0, viewMatrix, 0, modelMatrix, 0);
	    float[] normalMatrix = new float[9];
		Mat3x3.Mat3(mMVMatrix , temp);
	    Mat3x3.inverse(temp, temp, 9);
	    Mat3x3.transpose(temp, 9, normalMatrix);
		glUniformMatrix3fv(uModelInvMatrixHandler, 1, false, normalMatrix, 0);
		
		float[] viewInvMatrix = new float[16];
		Matrix.invertM(viewInvMatrix, 0, viewMatrix, 0);
		glUniformMatrix4fv(uViewInvMatrixHandler, 1, false, viewInvMatrix, 0);
		
		glDrawArrays(GL_TRIANGLES , 0, vertexCount);
		
		glDisableVertexAttribArray(aPositionHandler);
		glDisableVertexAttribArray(aNormalHandler);
		
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
	
	
	public void setProjectionMatrix(float[] projectionMatrix) {
		for(int i=0;i<projectionMatrix.length;i++){
			this.projectionMatrix[i] = projectionMatrix[i];
		}
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
	
	public void destroy(){
		//TODO comprobar referencias
		int[] buffers = new int[]{vertexBufferHandler};
		glDeleteBuffers(buffers.length, buffers, 0);
		shader.destroy();
	}
}
