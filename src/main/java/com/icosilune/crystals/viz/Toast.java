/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icosilune.crystals.viz;

import com.google.common.collect.ImmutableList;
import com.icosilune.crystals.math.Matrix3d;
import com.icosilune.crystals.math.Point3d;
import com.icosilune.crystals.math.lattices.CenteringType;
import com.icosilune.crystals.math.lattices.Lattice;
import com.icosilune.crystals.math.pointgroups.CrystalSystem;
import com.icosilune.crystals.math.pointgroups.PointGroup;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author ashmore
 */
public class Toast {
  
  public static void main(String args[]) {
    
    Renderer3d mainPanel = new Renderer3d();
    
    mainPanel.setPoints(genDots());
    mainPanel.setPreferredSize(new Dimension(500, 500));
    
    JFrame frame = new JFrame("meh");
    frame.add(mainPanel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }
  
  
  private static List<Dot> genDots() {
    
    List<Dot> dots = new ArrayList<>();
    
    Random r = new Random();
    
    Lattice lat = Lattice.monoclinic(1.0, 0.7, Math.PI/6, CenteringType.PRIMITIVE);
//    Lattice lat = Lattice.hexagonal(1.0, 0.5);
//    Lattice lat = Lattice.rhombohedral(1.0, Math.PI/3);
//    Lattice lat = Lattice.cubic(1.0, CenteringType.FACE_CENTERED);
    
    List<Point3d> points = new ArrayList<>();
    
    int N = 20;
    
    for (int i = -N; i < N; i++) {
      for (int j = -N; j < N; j++) {
        for (int k = -N; k < N; k++) {
          Point3d p = lat.generate(i, j, k);
          p = p.multiply(0.3);
          if (p.norm() > 1) {
            continue;
          }
          points.add(p);
        }
      }
    }

//    int k = 0;
//    while(points.size() < 1000 && k < 10000) {
//      k++;
//      
//      Point3d p = lat.generate(r.nextInt(200)-100, r.nextInt(200)-100, r.nextInt(200)-100);
//      p = p.multiply(0.01);
//      if(p.norm() > 1)
//        continue;
//      points.add(p);
//    }
    
    for (Point3d p : points) {
      dots.add(Dot.create(p, Color.WHITE, 1, Dot.CIRCLE));
    }
    
    return dots;
  }
  
//  
//  private static List<Dot> genDots() {
//    
//    List<Dot> dots = new ArrayList<>();
//    
//    Random r = new Random();
//    
//    CrystalSystem crystalSystem = CrystalSystem.TRIGONAL_DIPYRAMIDAL;
//    PointGroup pointGroup = crystalSystem.getPointGroup();
//    
//    for (int i = 0; i<100;i++) {
//      Point3d p = new Point3d(2*r.nextDouble()-1,2*r.nextDouble()-1,2*r.nextDouble()-1);
//      
//      Matrix3d m = pointGroup.entries().get(r.nextInt(pointGroup.entries().size()));
//      
//      Point3d x = m.apply(Point3d.UNIT_X).multiply(0.03);
//      Point3d y = m.apply(Point3d.UNIT_Y).multiply(0.03);
//      Point3d z = m.apply(Point3d.UNIT_Z).multiply(0.03);
//      
//      dots.add(Dot.create(p, Color.WHITE, 1, Dot.SQUARE));
//      
//      for(int k=1;k<5;k++) {
//        dots.add(Dot.create(p.add(x.multiply(k)), Color.RED, 1, Dot.CIRCLE));
//        dots.add(Dot.create(p.add(y.multiply(k)), Color.GREEN, 1, Dot.CIRCLE));
//        dots.add(Dot.create(p.add(z.multiply(k)), Color.BLUE, 1, Dot.CIRCLE));
//      }
//    }
//    
//    return dots;
//  }
  
//  private static List<Dot> genDots() {
//    return genPoints()
//          .stream()
//          .map(p -> Dot.create(p, Color.yellow, 1, Dot.CIRCLE))
//          .collect(ImmutableList.toImmutableList());
//  }
  
  private static List<Point3d> genPoints() {
    List<Point3d> points = new ArrayList<>();
    int N = 51;
    
    for(int i = 0; i<N;i++)
      for(int j = 0; j<N;j++) {
        double theta = Math.PI * 2 * i * (1.0/N);
        double phi = Math.PI * j * (1.0/N);
      
        Point3d p = new Point3d(Math.cos(theta), Math.sin(theta)*Math.cos(phi), Math.sin(theta)*Math.sin(phi));
        points.add(p);
      }
    return points;
  }
}
