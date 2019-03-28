/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icosilune.crystals.math;

import java.util.List;

/**
 *
 * @author ashmore
 */
public interface Group<T> {
  
  T identity();
  
  T apply(T a, T b);
  
  T inverse(T a);
  
  List<T> entries();
}
