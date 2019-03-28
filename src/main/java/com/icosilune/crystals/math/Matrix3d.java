/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icosilune.crystals.math;

import static com.icosilune.crystals.math.Util.equalsWithinTolerance;
import static com.icosilune.crystals.math.Util.toleranceHash;

/**
 * matrix for handling linear transformations for Point3d.
 */
public class Matrix3d {
  public static final Matrix3d IDENTITY = new Matrix3d(1,0,0,0,1,0,0,0,1);
  
  private final double m[][] = new double[3][3];

  public Matrix3d(
          double m00, double m01, double m02,
          double m10, double m11, double m12,
          double m20, double m21, double m22) {
    m[0][0] = m00;
    m[0][1] = m01;
    m[0][2] = m02;
    m[1][0] = m10;
    m[1][1] = m11;
    m[1][2] = m12;
    m[2][0] = m20;
    m[2][1] = m21;
    m[2][2] = m22;
  }

  public Point3d apply(Point3d in) {
    return new Point3d(
        in.getX()*m[0][0] + in.getY()*m[0][1] + in.getZ()*m[0][2],
        in.getX()*m[1][0] + in.getY()*m[1][1] + in.getZ()*m[1][2],
        in.getX()*m[2][0] + in.getY()*m[2][1] + in.getZ()*m[2][2]);
  }
  
  public Matrix3d apply(Matrix3d in) {
    // may have this backwards, may be the transpose
    return new Matrix3d(
        m[0][0]*in.m[0][0]+m[0][1]*in.m[1][0]+m[0][2]*in.m[2][0],
        m[1][0]*in.m[0][0]+m[1][1]*in.m[1][0]+m[1][2]*in.m[2][0],
        m[2][0]*in.m[0][0]+m[2][1]*in.m[1][0]+m[2][2]*in.m[2][0],
        
        m[0][0]*in.m[0][1]+m[0][1]*in.m[1][1]+m[0][2]*in.m[2][1],
        m[1][0]*in.m[0][1]+m[1][1]*in.m[1][1]+m[1][2]*in.m[2][1],
        m[2][0]*in.m[0][1]+m[2][1]*in.m[1][1]+m[2][2]*in.m[2][1],
        
        m[0][0]*in.m[0][2]+m[0][1]*in.m[1][2]+m[0][2]*in.m[2][2],
        m[1][0]*in.m[0][2]+m[1][1]*in.m[1][2]+m[1][2]*in.m[2][2],
        m[2][0]*in.m[0][2]+m[2][1]*in.m[1][2]+m[2][2]*in.m[2][2]
    ).transpose();
  }
  
  public Matrix3d transpose() {
    return new Matrix3d(
        m[0][0], m[1][0], m[2][0],
        m[0][1], m[1][1], m[2][1],
        m[0][2], m[1][2], m[2][2]
    );
  }

  /**
   * Rotate angle radians across axis.
   */
  public static Matrix3d rotation(Point3d axis, double angle) {
    axis = axis.normalize();
    double x = axis.getX();
    double y = axis.getY();
    double z = axis.getZ();
    double c = Math.cos(angle);
    double c1 = 1 - Math.cos(angle);
    double s = Math.sin(angle);
    return new Matrix3d(
            c + x*x*c1, x*y*c1 - z*s, x*z*c1 + y*s,
            y*x*c1 + z*s, c + y*y*c1, y*z*c1 - x*s,
            z*x*c1 - y*s, z*y*c1 + x*s, c + z*z*c1).transpose();
  }
  
  public static Matrix3d reflection(Point3d planeNormal) {
    planeNormal = planeNormal.normalize();
    double x = planeNormal.getX();
    double y = planeNormal.getY();
    double z = planeNormal.getZ();
    return new Matrix3d(
        1-2*x*x, -2*x*y, -2*x*z,
        -2*x*y, 1-2*y*y, -2*y*z,
        -2*x*z, -2*y*z, 1-2*z*z);
  }

  @Override
  public boolean equals(Object obj) {
    if(obj instanceof Matrix3d) {
      Matrix3d other = (Matrix3d) obj;
      
      for(int i=0;i<3;i++)
        for(int j=0;j<3;j++)
          if(!equalsWithinTolerance(m[i][j], other.m[i][j]))
            return false;
      return true;
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    int hash = 7;
    for(int i=0;i<3;i++)
      for(int j=0;j<3;j++)
        hash = 31*hash + toleranceHash(m[i][j]);
    return hash;
  }
  
  private static String FORMAT_BASE = "%f";
  private static String FORMAT = 
        "["+FORMAT_BASE+" "+FORMAT_BASE+" "+FORMAT_BASE+"]\n"
      + "["+FORMAT_BASE+" "+FORMAT_BASE+" "+FORMAT_BASE+"]\n"
      + "["+FORMAT_BASE+" "+FORMAT_BASE+" "+FORMAT_BASE+"]\n";

  @Override
  public String toString() {
    return String.format(FORMAT,
        m[0][0], m[0][1], m[0][2],
        m[1][0], m[1][1], m[1][2],
        m[2][0], m[2][1], m[2][2]);
  }
  
  public double sumDistance(Matrix3d other) {
    double distance = 0;
    for(int i=0;i<3;i++)
      for(int j=0;j<3;j++)
        distance += Math.abs(m[i][j] - other.m[i][j]);
    return distance;
  }
  
  public double maxDistance(Matrix3d other) {
    double distance = 0;
    for(int i=0;i<3;i++)
      for(int j=0;j<3;j++)
        distance = Math.max(distance, Math.abs(m[i][j] - other.m[i][j]));
    return distance;
  }
}
