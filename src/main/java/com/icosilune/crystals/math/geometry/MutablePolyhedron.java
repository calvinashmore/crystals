/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icosilune.crystals.math.geometry;

import com.google.common.base.Preconditions;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableList;
import com.icosilune.crystals.math.Point3d;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ashmore
 */
public class MutablePolyhedron {
  private Map<Point3d, MutableVertex> vertexMap = new HashMap<>();
  private BiMap<Integer, MutableVertex> vertexIndexMap = HashBiMap.create();
  private BiMap<Integer, MutableEdge> edgeIndexMap = HashBiMap.create();
  private BiMap<Integer, MutableFace> faceIndexMap = HashBiMap.create();
  
  /**
   * Add a face with the following vertices. The first vertex does not need to be repeated. Will
   * need to validate that points are coplanar.
   */
  void addFace(List<Point3d> facePoints) {
    
    Preconditions.checkArgument(facePoints.size() >= 3);
    
    // Add each vertex
    ImmutableList.Builder<MutableVertex> vertsBuilder = ImmutableList.builder();
    for (Point3d p : facePoints) {
      MutableVertex v = vertexMap.get(p);
      if (v == null) {
        v = new MutableVertex(p);
        vertexMap.put(p, v);
        vertexIndexMap.put(vertexIndexMap.size(), v);
      }
      vertsBuilder.add(v);
    }
    
    ImmutableList<MutableVertex> verts = vertsBuilder.build();

    MutableFace f = new MutableFace(verts);
    faceIndexMap.put(faceIndexMap.size(), f);
    
    for (int i = 0; i < verts.size(); i++) {
      MutableVertex v1 = verts.get(i);
      MutableVertex v2 = verts.get((i+1) % facePoints.size());
      MutableEdge e = getOrCreateEdge(v1, v2);
      e.addFace(f);
    }
  }
  
  private MutableEdge getOrCreateEdge(MutableVertex v1, MutableVertex v2) {
    MutableEdge edgeKey = new MutableEdge(v1, v2);
    if (edgeIndexMap.inverse().containsKey(edgeKey)) {
      return edgeIndexMap.get(edgeIndexMap.inverse().get(edgeKey));
    } else if (edgeIndexMap.inverse().containsKey(edgeKey.reverse())) {
      return edgeIndexMap.get(edgeIndexMap.inverse().get(edgeKey.reverse()));
    } else {
      MutableEdge edge = new MutableEdge(v1, v2);
      edgeIndexMap.put(edgeIndexMap.size(), edge);
      return edge;
    }
  }
}
