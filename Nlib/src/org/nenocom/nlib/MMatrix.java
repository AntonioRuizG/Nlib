/**
 * If debugging is the process of removing bugs, then programming must be the process of putting them in.
 * Edsger W. Dijkstra
 */
package org.nenocom.nlib;

/**
 * @author Antonio Ruiz
 *
 */
public class MMatrix {
	public static void setPerspective(float[] m, float yFovInDegrees,
			float aspectRatio, float near, float far) {
		float angleInRadians = (float) (yFovInDegrees * Math.PI / 180.0);
		float g = (float) (1.0 / Math.tan(angleInRadians / 2.0));
		m[0] = g / aspectRatio;
		m[1] = 0f;
		m[2] = 0f;
		m[3] = 0f;
		m[4] = 0f;
		m[5] = g;
		m[6] = 0f;
		m[7] = 0f;
		m[8] = 0f;
		m[9] = 0f;
		m[10] = -((far + near) / (far - near));
		m[11] = -1f;
		m[12] = 0f;
		m[13] = 0f;
		m[14] = -((2f * far * near) / (far - near));
		m[15] = 0f;
	}
}