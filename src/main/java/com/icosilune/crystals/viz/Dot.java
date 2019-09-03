/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icosilune.crystals.viz;

import com.google.auto.value.AutoValue;
import com.icosilune.crystals.math.Point3d;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author ashmore
 */
@AutoValue
public abstract class Dot {
  private static final int DOT_SIZE = 5;
  interface Renderable {
    void render(Graphics2D g);
  }
  
  public static final Renderable CIRCLE = g -> g.fillOval((int) (- DOT_SIZE/2),
              (int) (- DOT_SIZE/2),
              DOT_SIZE, DOT_SIZE);
  
  public static final Renderable SQUARE = g -> g.fillRect((int) (- DOT_SIZE/2),
              (int) (- DOT_SIZE/2),
              DOT_SIZE, DOT_SIZE);
  
  public abstract Point3d getPoint();
  public abstract Color getColor();
  public abstract double getSize();
  public abstract Renderable getRenderer();
  
  public static Dot create(Point3d point, Color color, double size, Renderable renderer) {
    return new AutoValue_Dot(point, color, size, renderer);
  }
}
