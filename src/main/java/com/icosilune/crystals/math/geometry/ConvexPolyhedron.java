/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icosilune.crystals.math.geometry;

import com.icosilune.crystals.math.geometry.MutableFace;
import com.icosilune.crystals.math.geometry.Line;
import com.icosilune.crystals.math.geometry.Plane;
import static com.icosilune.crystals.math.Util.equalsWithinTolerance;

import com.google.auto.value.AutoValue;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.icosilune.crystals.math.Point3d;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import javafx.collections.transformation.SortedList;

/**
 *
 * @author ashmore
 */
public class ConvexPolyhedron {
  // ****************
  
  // Maybe rename to PolyhedronUtils
  
  // generateConvexPolyhedron(planes)
  
  
  
  
//  private ImmutableList<Plane> planes;

//  public ConvexPolyhedron(Iterable<Plane> planes) {
//    this.planes = ImmutableList.copyOf(planes);
//  }
//  
//  private static ImmutableList<Face> calculateFaces(ImmutableList<Plane> planes) {
//    // Each plane should have exactly one face
//    // (if one doesn't then it's extraneous, or the planes don't form a closed polyhedron)
//    
//    return planes.stream()
//        .map(com.icosilune.crystals.grains.Plane plane -> calculateFace(plane, planes))
//        .collect(ImmutableList.toImmutableList());
//  }
//  
//  // NOTE: This method is not performant.
//  // We do this naively (for now) and perform many redundant calculations
//  private static Face calculateFace(Plane facePlane, ImmutableList<Plane> allPlanes) {
//    // facePlane will be included in allPlanes
//    
//    List<Line> lines = new ArrayList<>();
//    
//    // Start with a point that is on the face plane.
//    Point3d faceCenter = facePlane.getNormal().multiply(facePlane.getDistance());
//    
//    for (Plane intersectingPlane : allPlanes) {
//      if (intersectingPlane.equals(facePlane)) {
//        continue;
//      }
//      
//      // this vector is perpindicular to the face and intersecting plane normals, and must be the
//      // direction of the line
//      Point3d cross = facePlane.getNormal().cross(intersectingPlane.getNormal()).normalize();
//      // TO TEST: invalid cases
//      
//      // Now move faceCenter so that it lands on the other plane, but is still on the face plane.
//      // Need to find a vector that is perpindicular to the plane face plane normal
//      Point3d perpindicular = intersectingPlane.getNormal().subtract( facePlane.getNormal().multiply(
//          facePlane.getNormal().dot(intersectingPlane.getNormal())  ) ).normalize();
//      // When the normals are not orthogonal, there will be some progress along the perpindicular already
//      double perpDistance = intersectingPlane.getDistance() - faceCenter.dot(intersectingPlane.getNormal());
//      
//      // I hope this is correct
//      Point3d pointOnBothPlanes = faceCenter.add(perpindicular.multiply(perpDistance));
//      
//      
//      lines.add(Line.create(cross, pointOnBothPlanes));
//    }
//    
//    // The lines are all in 3d space, but we know they are coplanar on facePlane
//    // Now we need to find the smallest loop
//    // If lines are not parallel, each pair will have intersection
//    
//    List<Point3d> points = new ArrayList<>();
//    for(Line lineA : lines)
//      for(Line lineB : lines) {
//        if (lineA == lineB) {
//          continue;
//        }
//        Line.findIntersection(lineA, lineB).ifPresent(points::add);
//      }
//    
//    Preconditions.checkArgument(points.size() >= 3, "Can't create convex hull with fewer than 3 points");
//    
//    // okay, now.
//    // we want to find the MINIMUM set of points that contain a convex hull around the faceCenter
//    // Iterate through the points in order of closeness to faceCenter.
//    // If the point is inside of the hull, add it. Keep going until the hull is closed.
//    // going to make the assumption that a hull point can not be equal to the face center
//    Comparator<Point3d> distanceComparator = Comparator.comparingDouble(p -> p.subtract(faceCenter).norm());
//    points.sort(distanceComparator);
//    
//    // We sort the hull points themselves using an angle thingy
//    Point3d perpX = points.get(0).subtract(faceCenter).normalize();
//    Point3d perpY = perpX.cross(faceCenter);
//    
//    Comparator<Point3d> angleComparator = Comparator.comparingDouble(p -> Math.atan2(p.dot(perpX), p.dot(perpY)));
//    
//    Set<Point3d> hull = new TreeSet<>(angleComparator);
//    for (Point3d p : points) {
//      if (!containedInHull(faceCenter, hull, p))
//        continue;
//      
//      hull.add(p);
//      // potentially remove added point that shouldn't be there
//      if (hullIsClosed(faceCenter, hull))
//        break;
//    }
//    
//  }
  
  
  
  
}
