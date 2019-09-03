/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icosilune.crystals.math.geometry;

import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;

/**
 * Solid geometry composed of faces.
 */
//@AutoValue
public abstract class Polyhedron {
  public abstract ImmutableList<MutableFace> getFaces();
  public abstract ImmutableList<MutableVertex> getVertices();
  public abstract ImmutableList<MutableEdge> getEdges();
  
  
  // ****
  // Consider half-edges:
  // https://www.openmesh.org/media/Documentations/OpenMesh-Doc-Latest/a04074.html
  
}
