/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icosilune.crystals.math;

import com.google.common.truth.Truth;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 *
 * @author ashmore
 */
@RunWith(JUnit4.class)
public class Matrix3dTest {
  
  @Test
  public void testTranspose() {
    Matrix3d m = Matrix3d.rotation(Point3d.UNIT_Y, 0.1);
    Truth.assertThat(m.transpose()).isNotEqualTo(m);
    Truth.assertThat(m.transpose().transpose()).isEqualTo(m);
  }
  
  @Test
  public void testRotationCorrectness() {
    double theta = Math.PI/2;
    
    Matrix3d rotation = Matrix3d.rotation(Point3d.UNIT_Y, theta);
    Truth.assertThat(rotation.apply(Point3d.UNIT_X)).isEqualTo(Point3d.UNIT_Z);
  }
  
  @Test
  public void testRotationCorrectness1() {
    double theta = 2*Math.PI/3;
    
    Matrix3d rotation = Matrix3d.rotation(Point3d.UNIT_Y, theta);
    Truth.assertThat(rotation.apply(Point3d.UNIT_X)).isEqualTo(new Point3d(-0.5,0,Math.sqrt(3)/2));
  }
  
  @Test
  public void testRotationEquivalence() {
    int N = 31;
    double theta = 2*Math.PI/N;
    
    Matrix3d m = Matrix3d.rotation(Point3d.UNIT_Y, theta);
    Matrix3d test = Matrix3d.IDENTITY;
    
    for(int i=0;i<N;i++) {
      test = m.apply(test);
    }
    
    Truth.assertThat(test).isEqualTo(Matrix3d.IDENTITY);
  }
  
  @Test
  public void testReflectionCorrectness() {
    Matrix3d m = Matrix3d.reflection(Point3d.UNIT_X);
    Truth.assertThat(m.apply(new Point3d(1,1,1))).isEqualTo(new Point3d(-1,1,1));
    
    Point3d reflectionNormal = Matrix3d.rotation(Point3d.UNIT_Y, 2*Math.PI/3).apply(Point3d.UNIT_X);
    m = Matrix3d.reflection(reflectionNormal);
    Truth.assertThat(m.apply(Point3d.UNIT_X)).isEqualTo( Matrix3d.rotation(Point3d.UNIT_Y, Math.PI/3).apply(Point3d.UNIT_X));
  }
  
  @Test
  public void testReflectionEquivalence() {
    Point3d normal = new Point3d(0.4,0.6,0.8);
    Matrix3d reflect = Matrix3d.reflection(normal);
    Truth.assertThat(reflect.apply(reflect)).isEqualTo(Matrix3d.IDENTITY);
  }
}
