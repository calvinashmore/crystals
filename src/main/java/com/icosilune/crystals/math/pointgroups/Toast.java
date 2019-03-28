/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icosilune.crystals.math.pointgroups;

import com.google.common.collect.ImmutableMap;
import com.icosilune.crystals.math.Matrix3d;
import com.icosilune.crystals.math.Point3d;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author ashmore
 */
public class Toast {
  
  public static void main(String args[]) {
    PointGroup pg = new PointGroup(PointGroups.dihedral(2, PointGroups.ReflectionAxis.DIAGONAL));
    
    Solid solid = new Solid(ImmutableMap.<String, Point3d>builder()
        .put("A", new Point3d(-1, 1, -1))
        .put("B", new Point3d(1, 1, -1))
        .put("C", new Point3d(1, -1, -1))
        .put("D", new Point3d(-1, -1, -1))
        
        .put("A'", new Point3d(-1, 1, 1))
        .put("B'", new Point3d(1, 1, 1))
        .put("C'", new Point3d(1, -1, 1))
        .put("D'", new Point3d(-1, -1, 1))
        .build());
    
    for(Matrix3d a : pg.entries()) {
      Solid s = solid.transform(a);
//      System.out.println(s.getPoints());
      System.out.println(s.listPointsInOrder(solid));
    }
    
//    Map<Matrix3d, Integer> bleh = new HashMap<>();
//    int q = 1;
//    for(Matrix3d a : pg.entries()) {
//      bleh.put(a, q++);
//    }
    
//    for(Matrix3d a : pg.entries()) {
//      for(Matrix3d b : pg.entries()) {
//        System.out.printf("%s * %s = %s\n",
//            bleh.get(a),
//            bleh.get(b),
//            bleh.get(a.apply(b))
//            );
//      }
//    }

//    for (Set<Matrix3d> cycle : pg.getCycles()) {
//      System.out.println("cycle w length "+ cycle.size());
//    }
  }
}
