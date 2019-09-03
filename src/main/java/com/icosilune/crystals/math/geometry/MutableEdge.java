/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icosilune.crystals.math.geometry;

import com.google.auto.value.AutoValue;
import com.icosilune.crystals.math.Point3d;
import java.util.Objects;

/**
 *
 * @author ashmore
 */
public class MutableEdge {
  final MutableVertex vertex1;
  final MutableVertex vertex2;
  MutableFace face1;
  MutableFace face2;

  public MutableEdge(MutableVertex vertex1, MutableVertex vertex2) {
    this.vertex1 = vertex1;
    this.vertex2 = vertex2;
  }
  
  MutableEdge reverse() {
    MutableEdge next = new MutableEdge(this.vertex2, this.vertex1);
    next.face1 = this.face2;
    next.face2 = this.face1;
    return next;
  }
  
  void addFace(MutableFace f) {
    if (face1 != null) {
      face1 = f;
    } else if (face2 != null) {
      face2 = f;
    } else {
      // throw exception?
    }
  }

  // methods to turn into Line
  
  // How can we make these immutable?
  // A perverse way of thinking about this is that each of these classes (face, edge, vertex) is a
  // node in another graph; not sure if there is means of making immutable, though

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 53 * hash + Objects.hashCode(this.vertex1);
    hash = 53 * hash + Objects.hashCode(this.vertex2);
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
    final MutableEdge other = (MutableEdge) obj;
    if (!Objects.equals(this.vertex1, other.vertex1)) {
      return false;
    }
    if (!Objects.equals(this.vertex2, other.vertex2)) {
      return false;
    }
    return true;
  }
}
