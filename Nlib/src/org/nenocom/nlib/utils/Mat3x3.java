/**
 * The object-oriented model makes it easy to build up programs
 * by accretion. What this often means, in practice, is that 
 * it provides a structured way to write spaghetti code.
 * — Paul Graham
 *
 */
package org.nenocom.nlib.utils;

/**
 * @author Antonio Ruiz
 *
 */
public class Mat3x3 {
public static float determinant(float []m){
return 
        + m[0] * (m[4] * m[8] - m[7] * m[5])
        - m[3] * (m[1] * m[8] - m[7] * m[2])
        + m[6] * (m[1] * m[5] - m[4] * m[2]);
}
public static void Mat3(float []m4, float[]m){
m[0]=m4[0]; m[1]=m4[1]; m[2]=m4[2];
m[3]=m4[4]; m[4]=m4[5]; m[5]=m4[6];
m[6]=m4[8]; m[7]=m4[9]; m[8]=m4[10];
}
/*
    Inverse[0][0] = + (m[1][1] * m[2][2] - m[2][1] * m[1][2]);
    Inverse[1][0] = - (m[1][0] * m[2][2] - m[2][0] * m[1][2]);
    Inverse[2][0] = + (m[1][0] * m[2][1] - m[2][0] * m[1][1]);
    Inverse[0][1] = - (m[0][1] * m[2][2] - m[2][1] * m[0][2]);
    Inverse[1][1] = + (m[0][0] * m[2][2] - m[2][0] * m[0][2]);
    Inverse[2][1] = - (m[0][0] * m[2][1] - m[2][0] * m[0][1]);
    Inverse[0][2] = + (m[0][1] * m[1][2] - m[1][1] * m[0][2]);
    Inverse[1][2] = - (m[0][0] * m[1][2] - m[1][0] * m[0][2]);
    Inverse[2][2] = + (m[0][0] * m[1][1] - m[1][0] * m[0][1]);
    Inverse /= Determinant;
 */
public static void inverse(float []m, float[] Inverse, int offset){

float Determinant = Mat3x3.determinant(m);
Inverse[offset+0] = + (m[4] * m[8] - m[7] * m[5])/ Determinant;
Inverse[offset+3] = - (m[3] * m[8] - m[6] * m[5])/ Determinant;
Inverse[offset+6] = + (m[3] * m[7] - m[6] * m[4])/ Determinant;
Inverse[offset+1] = - (m[1] * m[8] - m[7] * m[2])/ Determinant;
Inverse[offset+4] = + (m[0] * m[8] - m[6] * m[2])/ Determinant;
Inverse[offset+7] = - (m[0] * m[7] - m[6] * m[1])/ Determinant;
Inverse[offset+2] = + (m[1] * m[5] - m[4] * m[2])/ Determinant;
Inverse[offset+5] = - (m[0] * m[5] - m[3] * m[2])/ Determinant;
Inverse[offset+8] = + (m[0] * m[4] - m[3] * m[1])/ Determinant;
}
public static void transpose(float []m, int offset, float[]result){
result[0] = m[offset+0];
result[1] = m[offset+3];
result[2] = m[offset+6];

result[3] = m[offset+1];
result[4] = m[offset+4];
result[5] = m[offset+7];

result[6] = m[offset+2];
result[7] = m[offset+5];
result[8] = m[offset+8];
}
}