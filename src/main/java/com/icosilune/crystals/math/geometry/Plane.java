/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icosilune.crystals.math.geometry;

import com.google.auto.value.AutoValue;
import com.icosilune.crystals.math.Point3d;

/**
 *
 * @author ashmore
 */
@AutoValue
public abstract class Plane {
  
  public abstract Point3d getNormal();

  public abstract double getDistance();

  static Plane create(Point3d normal, double distance) {
    return new AutoValue_Plane(normal.normalize(), distance);
  }

  public double getDistanceFromVector(Point3d vector) {
    // such that distanceFromVector * vector is a point on the plane
    // if vector is perpindicular then this will be infinite.
    return getDistance() / (1 - getNormal().dot(vector));
  }
  
  // intersect with line
  
  // can also generate from 3 points?
}
