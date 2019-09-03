/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icosilune.crystals.math.geometry;

import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;
import com.icosilune.crystals.math.Point3d;
import com.icosilune.crystals.math.geometry.Plane;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ashmore
 */
class MutableFace {
  
  final ImmutableList<MutableVertex> vertices; // Should be in counter-clockwise order?!

  public MutableFace(ImmutableList<MutableVertex> vertices) {
    this.vertices = vertices;
  }
  
  void validate() {
    // assert that points are coplanar
  }
  
//  public abstract ImmutableList<Edge> getEdges();
////  public abstract 
//
//  public abstract Plane getPlane();
  
  // triangulate?
}
