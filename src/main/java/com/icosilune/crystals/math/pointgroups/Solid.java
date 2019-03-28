/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icosilune.crystals.math.pointgroups;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.icosilune.crystals.math.Matrix3d;
import com.icosilune.crystals.math.Point3d;
import java.util.Map;
import java.util.function.Function;

/**
 *
 * @author ashmore
 */
public class Solid {
  private final ImmutableBiMap<String, Point3d> points;

  public Solid(Map<String, Point3d> points) {
    this.points = ImmutableBiMap.copyOf(points);
  }

  public ImmutableBiMap<String, Point3d> getPoints() {
    return points;
  }
  
  public Solid transform(Matrix3d transformation) {
    return new Solid(Maps.transformValues(points, transformation::apply));
  }
  
  public String listPointsInOrder(Solid reference) {
    StringBuilder sb = new StringBuilder();
    for (Point3d point : reference.points.values()) {
      String correspondingPointName = points.inverse().get(point);
      sb.append(correspondingPointName + " ");
    }
    return sb.toString();
  }
}
