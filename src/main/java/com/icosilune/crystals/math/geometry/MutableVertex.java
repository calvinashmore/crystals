/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icosilune.crystals.math.geometry;

import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;
import com.icosilune.crystals.math.Point3d;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author ashmore
 */
//@AutoValue
class MutableVertex {
  
  final Point3d point;
  List<Integer> faceIndices = new ArrayList<>();
  List<Integer> edgeIndices = new ArrayList<>();

  public MutableVertex(Point3d point) {
    this.point = point;
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 37 * hash + Objects.hashCode(this.point);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final MutableVertex other = (MutableVertex) obj;
    if (!Objects.equals(this.point, other.point)) {
      return false;
    }
    return true;
  }
}
