/**
 * Talk is cheap. Show me the code." 
 * - Linus Torvalds
 */
package org.nenocom.nlib.geometry;

/**
 * @author Antonio Ruiz
 *
 */
public class Point3f {

	public float x;
	public float y;
	public float z;
	
	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	public Point3f(float x, float y, float z) {
		this.x=x;
		this.y=y;
		this.z=z;
	}
	
	/**
	 * @param p2
	 * @return
	 */
	public Point3f getMiddlePoint(Point3f p2) {
		return new Point3f(
				(x+p2.x)/2f,
				(y+p2.y)/2f,
				(z+p2.z)/2f
				);
	}

	/**
	 * @return
	 */
	public Point3f normalize() {
		float mod = (float)Math.sqrt((x*x)+(y*y)+(z*z));
		if(mod == 0){
			return new Point3f(x, y, z);
		}
		return new Point3f(
				x/mod,
				y/mod,
				z/mod
				);
	}

}
