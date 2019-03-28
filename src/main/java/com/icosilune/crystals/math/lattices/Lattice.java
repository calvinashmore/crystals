/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icosilune.crystals.math.lattices;

import com.google.auto.value.AutoValue;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.icosilune.crystals.math.CrystalSystemType;
import com.icosilune.crystals.math.Matrix3d;
import com.icosilune.crystals.math.Point3d;

/**
 *
 * @author ashmore
 */
@AutoValue
public abstract class Lattice {
  
  public static Lattice cubic(double a, CenteringType centeringType) {
    Preconditions.checkArgument(centeringType == CenteringType.PRIMITIVE || centeringType == CenteringType.BODY_CENTERED || centeringType == CenteringType.FACE_CENTERED);
    
    return generalLattice(CrystalSystemType.HEXAGONAL, centeringType,  a,a,a, Math.PI/2,Math.PI/2,Math.PI/2);
  }
  
  public static Lattice hexagonal(double a, double c) {
    Preconditions.checkArgument(c!= a, "distances must be different");
    
    return generalLattice(CrystalSystemType.HEXAGONAL, CenteringType.PRIMITIVE,  a,a,c, Math.PI/2,Math.PI/2,Math.PI/3);
  }
  
  public static Lattice rhombohedral(double a, double alpha) {
    Preconditions.checkArgument(alpha != Math.PI/2, "none of the angles can be 90 degrees");
    
    return generalLattice(CrystalSystemType.TETRAGONAL, CenteringType.PRIMITIVE,  a,a,a, alpha,alpha,alpha);
  }
  
  public static Lattice tegragonal(double a, double c, CenteringType centeringType) {
    Preconditions.checkArgument(c!= a, "distances must be different");
    Preconditions.checkArgument(centeringType == CenteringType.PRIMITIVE || centeringType == CenteringType.BODY_CENTERED);
    
    return generalLattice(CrystalSystemType.TETRAGONAL, centeringType,  a,a,c, Math.PI/2, Math.PI/2, Math.PI/2);
  }
  
  public static Lattice orthorhombic(double a, double b, double c, CenteringType centeringType) {
    Preconditions.checkArgument(a != b && b != c && c!= a, "distances must be different");
    
    return generalLattice(CrystalSystemType.ORTHORHOMBIC, centeringType,  a,b,c, Math.PI/2, Math.PI/2, Math.PI/2);
  }
  
  public static Lattice monoclinic(double a, double c, double beta, CenteringType centeringType) {
    Preconditions.checkArgument(beta != Math.PI/2, "none of the angles can be 90 degrees");
    Preconditions.checkArgument(a != c, "distances must be different");
    Preconditions.checkArgument(centeringType == CenteringType.PRIMITIVE || centeringType == CenteringType.BASE_CENTERED);
    
    return generalLattice(CrystalSystemType.MONOCLINIC, centeringType, a,a,c, Math.PI/2,beta,Math.PI/2);
  }
  
  public static Lattice triclinic(double a, double b, double c, double alpha, double beta, double gamma) {
    Preconditions.checkArgument(alpha != Math.PI/2 && beta != Math.PI/2 && gamma != Math.PI/2, "none of the angles can be 90 degrees");
    Preconditions.checkArgument(a != b && b != c && c!= a, "distances must be different");
    
    return generalLattice(CrystalSystemType.TRICLINIC, CenteringType.PRIMITIVE,  a,b,c, alpha,beta,gamma);
  }
  
  private static Lattice generalLattice(CrystalSystemType system, CenteringType centeringType, double a, double b, double c, double alpha, double beta, double gamma) {
    // alpha is angle between x & y
    // beta is angle between y & z
    // gamma is angle between x & z
    // a is length of z
    // b is length of x
    // c is length of y
    
    Point3d x = Point3d.UNIT_X.multiply(b);
    Point3d y = Matrix3d.rotation(Point3d.UNIT_X, Math.PI/2 - beta).apply(
        Matrix3d.rotation(Point3d.UNIT_Z, alpha).apply(Point3d.UNIT_X.multiply(c)));
    Point3d z = Matrix3d.rotation(Point3d.UNIT_Y, gamma).apply(Point3d.UNIT_X.multiply(a));
    
    Matrix3d generator = new Matrix3d(
        x.getX(), x.getY(), x.getZ(),
        y.getX(), y.getY(), y.getZ(),
        z.getX(), z.getY(), z.getZ());
    
    return create(system, centeringType, generator);
  }
  
  public abstract CrystalSystemType getSystem();
  public abstract CenteringType getCenteringType();
  public abstract Matrix3d getGeneratorMatrix();
  
  static Lattice create(CrystalSystemType system, CenteringType centeringType, Matrix3d generatorMatrix) {
    return new AutoValue_Lattice(system,centeringType,generatorMatrix);
  }
  
  public ImmutableSet<Point3d> getGenerators() {
    ImmutableSet.Builder<Point3d> generators = ImmutableSet.builder();
    
    generators.add(getGeneratorMatrix().apply(Point3d.UNIT_X));
    generators.add(getGeneratorMatrix().apply(Point3d.UNIT_Y));
    generators.add(getGeneratorMatrix().apply(Point3d.UNIT_Z));
    
    if (getCenteringType() == CenteringType.BASE_CENTERED) {
      Point3d baseCenter = 
               getGeneratorMatrix().apply(Point3d.UNIT_X)
          .add(getGeneratorMatrix().apply(Point3d.UNIT_Z)).multiply(0.5);
      generators.add(baseCenter);
    } else if (getCenteringType() == CenteringType.BODY_CENTERED) {
      Point3d bodyCenter = 
               getGeneratorMatrix().apply(Point3d.UNIT_X)
          .add(getGeneratorMatrix().apply(Point3d.UNIT_Y))
          .add(getGeneratorMatrix().apply(Point3d.UNIT_Z)).multiply(0.5);
      generators.add(bodyCenter);
    } else if (getCenteringType() == CenteringType.FACE_CENTERED) {
      Point3d xyCenter = 
               getGeneratorMatrix().apply(Point3d.UNIT_X)
          .add(getGeneratorMatrix().apply(Point3d.UNIT_Y)).multiply(0.5);
      Point3d yzCenter = 
               getGeneratorMatrix().apply(Point3d.UNIT_Y)
          .add(getGeneratorMatrix().apply(Point3d.UNIT_Z)).multiply(0.5);
      Point3d zxCenter = 
               getGeneratorMatrix().apply(Point3d.UNIT_Z)
          .add(getGeneratorMatrix().apply(Point3d.UNIT_X)).multiply(0.5);
      generators.add(xyCenter);
      generators.add(yzCenter);
      generators.add(zxCenter);
    }
    
    return generators.build();
  }
  
  public Point3d generateCorner(int x, int y, int z) {
    Point3d p = new Point3d(x,y,z);
    return getGeneratorMatrix().apply(p);
  }
}
