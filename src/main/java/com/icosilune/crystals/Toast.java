/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icosilune.crystals;

import com.google.common.collect.ImmutableSet;
import com.icosilune.crystals.math.Point3d;
import com.icosilune.crystals.math.lattices.Lattice;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author ashmore
 */
public class Toast {
  public static void main(String args[]) {
    
    MainPanel mainPanel = new MainPanel();

    JFrame frame = new JFrame("meh");
    frame.add(mainPanel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }
  
  private static class MainPanel extends JPanel {
    private static final int INITIAL_RES = 800;
    
    @Override
    public Dimension getPreferredSize() {
      return new Dimension(INITIAL_RES, INITIAL_RES);
    }

    @Override
    public void paint(Graphics g) {
      Graphics2D g2 = (Graphics2D) g;
      ImmutableSet<Point3d> generators = Lattice.hexagonal(1.0, 0.5).getGenerators();
      
      for(Point3d p : generators) {
//        int x = p.getX();
//        
//        g2.fillOval(x, y, 10, 10);
      }
      
      
    }
  }
}
