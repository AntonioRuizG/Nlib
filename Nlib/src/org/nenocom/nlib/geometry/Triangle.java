/**
 * Talk is cheap. Show me the code." 
 * - Linus Torvalds
 */
package org.nenocom.nlib.geometry;

/**
 * @author Antonio Ruiz
 *
 */
public class Triangle {

	public Point3f p1;
	public Point3f p2;
	public Point3f p3;
	
	/**
	 * @param p12
	 * @param p122
	 * @param p13
	 */
	public Triangle(Point3f p1, Point3f p2, Point3f p3) {
		this.p1=p1;
		this.p2=p2;
		this.p3=p3;
	}
	
}
