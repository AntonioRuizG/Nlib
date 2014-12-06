/**
 * Talk is cheap. Show me the code." 
 * - Linus Torvalds
 */
package org.nenocom.nlib.geometry;

import java.util.ArrayList;

/**
 * @author Antonio Ruiz
 *
 */
public class ObjectBuilder {
	
	
	
	public static float[] getIcosphere(int recursiveDivisions){
		float[] vertices = null;
		ArrayList<Triangle> triangles = new ArrayList<Triangle>();
		generateIcosphere(triangles, recursiveDivisions);
		vertices = asFloatVertices(triangles);
		return vertices;
	}

	/**
	 * @param triangulos
	 * @return
	 */
	public static float[] asFloatVertices(ArrayList<Triangle> triangles) {
		float[] vertices = new float[triangles.size()*3*3];
		int offset = 0;
		for(Triangle t: triangles){
			vertices[offset++] = t.p1.x;
			vertices[offset++] = t.p1.y;
			vertices[offset++] = t.p1.z;
			
			vertices[offset++] = t.p2.x;
			vertices[offset++] = t.p2.y;
			vertices[offset++] = t.p2.z;
			
			vertices[offset++] = t.p3.x;
			vertices[offset++] = t.p3.y;
			vertices[offset++] = t.p3.z;
		}
		return vertices;
	}

	/**
	 * @param triangles
	 * @param recDiv
	 */
	private static void generateIcosphere(ArrayList<Triangle> triangles, int recDiv) {
		if(recDiv-- == 0){
			return;
		}
		if(triangles.isEmpty()){
			genIcosaedron(triangles);
		}
		Triangle t;
		for(int i=triangles.size()-1; i>=0; i--){
			t = triangles.remove(i);
			
			Point3f p12 = t.p1.getMiddlePoint(t.p2).normalize();
			Point3f p13 = t.p1.getMiddlePoint(t.p3).normalize();
			Point3f p23 = t.p2.getMiddlePoint(t.p3).normalize();
			
			Triangle t1 = new Triangle(t.p1, p12, p13);
			Triangle t2 = new Triangle(t.p2, p23, p12);
			Triangle t3 = new Triangle(t.p3, p13, p23);
			Triangle t4 = new Triangle(p12, p23, p13);
			
			triangles.add(t1);
			triangles.add(t2);
			triangles.add(t3);
			triangles.add(t4);
		}
		generateIcosphere(triangles, recDiv);
	}

	/**
	 * 
	 */
	private static void genIcosaedron(ArrayList<Triangle> triangles) {
		
        float t = (float) ((1f + Math.sqrt(5f)) / 2f);
 
        Point3f p0 = new Point3f(-1f,  t,  0f).normalize();
        Point3f p1 = new Point3f( 1f,  t,  0f).normalize();
        Point3f p2 = new Point3f(-1f, -t,  0f).normalize();
        Point3f p3 = new Point3f( 1f, -t,  0f).normalize();
 
        Point3f p4 = new Point3f( 0f, -1f,  t).normalize();
        Point3f p5 = new Point3f( 0f,  1f,  t).normalize();
        Point3f p6 = new Point3f( 0f, -1f, -t).normalize();
        Point3f p7 = new Point3f( 0f,  1f, -t).normalize();
 
        Point3f p8 = new Point3f( t,  0f, -1f).normalize();
        Point3f p9 = new Point3f( t,  0f,  1f).normalize();
        Point3f p10 = new Point3f(-t,  0f, -1f).normalize();
        Point3f p11 = new Point3f(-t,  0f,  1f).normalize();
 
        triangles.add(new Triangle(p0, p11, p5));
        triangles.add(new Triangle(p0, p5, p1));
        triangles.add(new Triangle(p0, p1, p7));
        triangles.add(new Triangle(p0, p7, p10));
        triangles.add(new Triangle(p0, p10, p11));
 
        triangles.add(new Triangle(p1, p5, p9));
        triangles.add(new Triangle(p5, p11, p4));
        triangles.add(new Triangle(p11, p10, p2));
        triangles.add(new Triangle(p10, p7, p6));
        triangles.add(new Triangle(p7, p1, p8));
 
        triangles.add(new Triangle(p3, p9, p4));
        triangles.add(new Triangle(p3, p4, p2));
        triangles.add(new Triangle(p3, p2, p6));
        triangles.add(new Triangle(p3, p6, p8));
        triangles.add(new Triangle(p3, p8, p9));
 
        triangles.add(new Triangle(p4, p9, p5));
        triangles.add(new Triangle(p2, p4, p11));
        triangles.add(new Triangle(p6, p2, p10));
        triangles.add(new Triangle(p8, p6, p7));
        triangles.add(new Triangle(p9, p8, p1));
	}

	/**
	 * @param triangles
	 * @return
	 */
	public static float[] asFloatLines(ArrayList<Triangle> triangles) {
		float[] vertices = new float[triangles.size()*3*6];
		int offset = 0;
		for(Triangle t: triangles){
			vertices[offset++] = t.p1.x;
			vertices[offset++] = t.p1.y;
			vertices[offset++] = t.p1.z;
			
			vertices[offset++] = t.p2.x;
			vertices[offset++] = t.p2.y;
			vertices[offset++] = t.p2.z;
			
			vertices[offset++] = t.p2.x;
			vertices[offset++] = t.p2.y;
			vertices[offset++] = t.p2.z;
			
			vertices[offset++] = t.p3.x;
			vertices[offset++] = t.p3.y;
			vertices[offset++] = t.p3.z;
			
			vertices[offset++] = t.p3.x;
			vertices[offset++] = t.p3.y;
			vertices[offset++] = t.p3.z;
			
			vertices[offset++] = t.p1.x;
			vertices[offset++] = t.p1.y;
			vertices[offset++] = t.p1.z;
		}
		return vertices;
		
	}
}
