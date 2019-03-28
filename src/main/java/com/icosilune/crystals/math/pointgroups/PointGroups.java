/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icosilune.crystals.math.pointgroups;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.truth.Truth;
import com.icosilune.crystals.math.Matrix3d;
import com.icosilune.crystals.math.Point3d;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author ashmore
 */
class PointGroups {
  private PointGroups() {}
  
  private static final Matrix3d REFLECTION_Y = Matrix3d.reflection(Point3d.UNIT_Y);
  
  public enum ReflectionAxis {
    NONE,
    HORIZONTAL,
    VERTICAL,
    DIAGONAL,
  }
  
  // Defines C_n groups
  public static ImmutableSet<Matrix3d> cyclic(int n, ReflectionAxis reflection) {
    
    Preconditions.checkArgument(
        reflection == ReflectionAxis.NONE
     || reflection == ReflectionAxis.HORIZONTAL
     || reflection == ReflectionAxis.VERTICAL,
        "%s inappropriate reflection axis for cyclic group", reflection);
    
    double theta = 2*Math.PI/n;
    
    ImmutableSet.Builder<Matrix3d> group = ImmutableSet.builder();
    for(int i=0;i<n;i++) {
      Matrix3d rotation = Matrix3d.rotation(Point3d.UNIT_Y, theta*i);
      group.add(rotation);
      
      if (reflection == ReflectionAxis.HORIZONTAL) {
        group.add(REFLECTION_Y.apply(rotation));
      } else if (reflection == ReflectionAxis.VERTICAL) {
        Point3d reflectionNormal = rotation.apply(Point3d.UNIT_Z);
        group.add(Matrix3d.reflection(reflectionNormal));
      }
    }
    
    return group.build();
  }
  
  // Defines S_2n groups
  public static ImmutableSet<Matrix3d> mirror(int n) {
    Preconditions.checkArgument(n%2 == 0, "S groups must have even n");
    
    double theta = 2*Math.PI/n;
    
    ImmutableSet.Builder<Matrix3d> group = ImmutableSet.builder();
    for (int i = 0; i < n; i++) {
      Matrix3d rotation = Matrix3d.rotation(Point3d.UNIT_Y, theta * i);
      if (i % 2 == 1) {
        group.add(REFLECTION_Y.apply(rotation));
      } else {
        group.add(rotation);
      }
    }
    return group.build();
  }
  
  // Defines D_n groups
  public static ImmutableSet<Matrix3d> dihedral(int n, ReflectionAxis reflection) {
    
    Preconditions.checkArgument(
        reflection == ReflectionAxis.NONE
     || reflection == ReflectionAxis.HORIZONTAL
     || reflection == ReflectionAxis.DIAGONAL,
        "%s inappropriate reflection axis for dihedral group", reflection);
    
    double theta = 2*Math.PI/n;
    
    ImmutableSet.Builder<Matrix3d> group = ImmutableSet.builder();
    for(int i=0;i<n;i++) {
      Matrix3d rotation = Matrix3d.rotation(Point3d.UNIT_Y, theta*i);
      group.add(rotation);
      
      Point3d nextAxis = rotation.apply(Point3d.UNIT_X);
      Matrix3d axisRotation = Matrix3d.rotation(nextAxis, Math.PI);
      group.add(axisRotation);
      
      if (reflection == ReflectionAxis.HORIZONTAL) {
        group.add(REFLECTION_Y.apply(rotation));
      } else if (reflection == ReflectionAxis.DIAGONAL) {
        double diagonalFold = n%2==0 ? 0.25 : 0.5;
        Point3d reflectionNormal = Matrix3d.rotation(Point3d.UNIT_Y, theta*(i+diagonalFold)).apply(Point3d.UNIT_X);
        group.add(Matrix3d.reflection(reflectionNormal));
      }
    }
    
    // For some reason these don't seem to close organically.
    return close(close(group.build()));
  }
  
  private static ImmutableSet<Matrix3d> close(Set<Matrix3d> group) {
    // This isn't a full closure but it seems to be good enough
    ImmutableSet.Builder<Matrix3d> builder = ImmutableSet.builder();
    for (Matrix3d a : group) {
      for (Matrix3d b : group) {
        Matrix3d c = a.apply(b);
        builder.add(c);
      }
    }
    return builder.build();
  }
}
