/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icosilune.crystals.math;

import java.util.Objects;

/**
 *
 * @author ashmore
 */
public class Util {
  private Util() {}

  // tolerance for equals and hashCode
  public static final double TOLERANCE = .00001;
  
  public static boolean equalsWithinTolerance(double a, double b) {
    return Math.abs(a-b) < TOLERANCE;
  }

  public static int toleranceHash(double a) {
    return (int) Math.round(a/TOLERANCE);
  }
}
