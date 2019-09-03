/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icosilune.crystals.math.geometry;

import com.google.auto.value.AutoValue;
import com.icosilune.crystals.math.Point3d;
import com.icosilune.crystals.math.Util;
import java.util.Optional;

/**
 *
 * @author ashmore
 */
@AutoValue
public abstract class Line {
  
  public abstract Point3d getDirectional();

  public abstract Point3d getStartingPoint();

  static Line create(Point3d directional, Point3d startingPoint) {
    return new AutoValue_Line(directional, startingPoint);
  }

  // Assuming the lines are coplanar...
  static Optional<Point3d> findIntersection(Line lineA, Line lineB) {
    Point3d a = lineA.getDirectional();
    Point3d b = lineB.getDirectional();
    // cosine of an angle is equal to the dot products of the unit vectors
    double cosGamma = a.dot(b);
    if (Util.equalsWithinTolerance(cosGamma, 1.0)) {
      // meaning the lines are parallel
      return Optional.empty();
    }
    if (lineA.getStartingPoint().equals(lineB.getStartingPoint())) {
      return Optional.of(lineA.getStartingPoint());
    }
    Point3d c = lineA.getStartingPoint().subtract(lineB.getStartingPoint());
    double distC = c.norm();
    c = c.multiply(1.0 / distC);
    // Trigonometry!
    // a, b, c are unit vectors for the line directions.
    // A, B, C are the points in space.
    // dist A is the distance between A and C (I know that's confusing)
    // alpha, beta, gamma are the angles at A, B, C
    // beta: angle between A and C (starting points in space)
    double cosBeta = b.dot(c);
    double sinBeta = Math.sqrt(1 - cosBeta * cosBeta);
    double sinGamma = Math.sqrt(1 - cosGamma * cosGamma);
    // by the law of sines, the distance along b will be given by this:
    double distB = distC * sinBeta / sinGamma;
    return Optional.of(lineA.getStartingPoint().add(a.multiply(distB)));
  }
}
