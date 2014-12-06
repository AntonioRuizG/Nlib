/**
 * The object-oriented model makes it easy to build up programs
 * by accretion. What this often means, in practice, is that 
 * it provides a structured way to write spaghetti code.
 * — Paul Graham
 *
 */
package org.nenocom.nlib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.nenocom.nlib.geometry.ObjectBuilder;
import org.nenocom.nlib.geometry.Point3f;
import org.nenocom.nlib.geometry.Triangle;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

/**
 * @author Antonio Ruiz
 *
 */
public class ObjLoader {

	private static Context context;
	private static HashMap<String, Integer> database = new HashMap<String, Integer>();

	/**
	 * @param key
	 * @return
	 */
	public static float[] LoadVertices(String key) {
		ArrayList<Point3f> vertices = new ArrayList<Point3f>();
		ArrayList<Triangle> triangles = new ArrayList<Triangle>();
		
		int resourceId = database.get(key);
		try {
			InputStream inputStream = context.getResources().openRawResource(
					resourceId );
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream);
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			
			String nextLine;
			while ((nextLine = bufferedReader.readLine()) != null) {
				if(nextLine.startsWith("v")){
					//es un vertice
					String[] str = nextLine.split(" ");
					float x = Float.parseFloat(str[1]);
					float y = Float.parseFloat(str[2]);
					float z = Float.parseFloat(str[3]);
					Point3f p = new Point3f(x, y, z);
					vertices.add(p);
				}else if(nextLine.startsWith("vn")){
					//es una normal
					
				}else if(nextLine.startsWith("f")){
					//es un triangulo v1//vn1 v2//vn2 v3//vn3
					String[] str = nextLine.split(" ");
					int p1Index = Integer.parseInt(str[1].split("//")[0]);
					int p2Index = Integer.parseInt(str[2].split("//")[0]);
					int p3Index = Integer.parseInt(str[3].split("//")[0]);
					Triangle t = new Triangle(
							vertices.get(p1Index-1),
							vertices.get(p2Index-1),
							vertices.get(p3Index-1));
					triangles.add(t);
				}
			}

		} catch (IOException e) {
			throw new RuntimeException(
					"Could not open resource: " + resourceId, e);
		} catch (Resources.NotFoundException nfe) {
			throw new RuntimeException("Resource not found: " + resourceId, nfe);
		}

		return ObjectBuilder.asFloatVertices(triangles);
	}


	public static void putObject(String key, int value){
		database.put(key, value);
	}
	
	public static void setContext(Context context){
		ObjLoader.context = context;
	}


	/**
	 * @param string
	 * @return
	 */
	public static float[] LoadNormals(String key) {
		ArrayList<Point3f> vertices = new ArrayList<Point3f>();
		ArrayList<Triangle> triangles = new ArrayList<Triangle>();
		
		int resourceId = database.get(key);
		try {
			InputStream inputStream = context.getResources().openRawResource(
					resourceId );
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream);
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			
			String nextLine;
			while ((nextLine = bufferedReader.readLine()) != null) {
				if(nextLine.startsWith("v")){
					//es un vertice
					
				}
				if(nextLine.startsWith("vn")){
					//es una normal
					String[] str = nextLine.split(" ");
					float x = Float.parseFloat(str[1]);
					float y = Float.parseFloat(str[2]);
					float z = Float.parseFloat(str[3]);
					Point3f p = new Point3f(x, y, z);
					vertices.add(p);
					
				}
				if(nextLine.startsWith("f")){
					//es un triangulo v1//vn1 v2//vn2 v3//vn3
					String[] str = nextLine.split(" ");
					int p1Index = Integer.parseInt(str[1].split("//")[1]);
					int p2Index = Integer.parseInt(str[2].split("//")[1]);
					int p3Index = Integer.parseInt(str[3].split("//")[1]);
					Triangle t = new Triangle(
							vertices.get(p1Index-1),
							vertices.get(p2Index-1),
							vertices.get(p3Index-1));
					triangles.add(t);
				}
			}

		} catch (IOException e) {
			throw new RuntimeException(
					"Could not open resource: " + resourceId, e);
		} catch (Resources.NotFoundException nfe) {
			throw new RuntimeException("Resource not found: " + resourceId, nfe);
		}

		return ObjectBuilder.asFloatVertices(triangles);
	}
}
