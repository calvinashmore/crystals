/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icosilune.crystals.math.pointgroups;

import com.google.common.collect.ImmutableSet;
import com.google.common.truth.Truth;
import com.icosilune.crystals.math.Matrix3d;
import com.icosilune.crystals.math.pointgroups.PointGroups;
import java.util.Set;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 *
 * @author ashmore
 */
@RunWith(JUnit4.class)
public class PointGroupsTest {
  
  private void checkClosure(Set<Matrix3d> group) {
    for(Matrix3d a : group)
      for(Matrix3d b : group){
        Matrix3d c = a.apply(b);
        Truth.assertThat(group).contains(c);
      }
  }
  
  @Test
  public void testCyclic() {
    checkClosure(PointGroups.cyclic(5, PointGroups.ReflectionAxis.NONE));
  }
  
  @Test
  public void testCyclic_horizontal() {
    checkClosure(PointGroups.cyclic(5, PointGroups.ReflectionAxis.HORIZONTAL));
  }
  
  @Test
  public void testCyclic_vertical() {
    checkClosure(PointGroups.cyclic(5, PointGroups.ReflectionAxis.VERTICAL));
  }
  
  @Test
  public void testMirror() {
    checkClosure(PointGroups.mirror(6));
  }
  
  @Test
  public void testDihedral() {
    checkClosure(PointGroups.dihedral(5, PointGroups.ReflectionAxis.NONE));
    Truth.assertThat(PointGroups.dihedral(2, PointGroups.ReflectionAxis.NONE)).hasSize(4);
    Truth.assertThat(PointGroups.dihedral(3, PointGroups.ReflectionAxis.NONE)).hasSize(6);
  }
  
  @Test
  public void testDihedral_horizontal() {
    checkClosure(PointGroups.dihedral(2, PointGroups.ReflectionAxis.HORIZONTAL));
    checkClosure(PointGroups.dihedral(3, PointGroups.ReflectionAxis.HORIZONTAL));
    Truth.assertThat(PointGroups.dihedral(2, PointGroups.ReflectionAxis.HORIZONTAL)).hasSize(8);
    Truth.assertThat(PointGroups.dihedral(3, PointGroups.ReflectionAxis.HORIZONTAL)).hasSize(12);
  }
  
  @Test
  public void testDihedral_diagonal() {
    checkClosure(PointGroups.dihedral(2, PointGroups.ReflectionAxis.DIAGONAL));
    checkClosure(PointGroups.dihedral(3, PointGroups.ReflectionAxis.DIAGONAL));
    Truth.assertThat(PointGroups.dihedral(2, PointGroups.ReflectionAxis.DIAGONAL)).hasSize(8);
    Truth.assertThat(PointGroups.dihedral(3, PointGroups.ReflectionAxis.DIAGONAL)).hasSize(12);
  }
  
  @Test
  public void testDihedral_distinct() {
    Truth.assertThat(
        PointGroups.dihedral(2, PointGroups.ReflectionAxis.DIAGONAL))
      .isNotEqualTo(
        PointGroups.dihedral(2, PointGroups.ReflectionAxis.HORIZONTAL));
    Truth.assertThat(
        PointGroups.dihedral(3, PointGroups.ReflectionAxis.DIAGONAL))
      .isNotEqualTo(
        PointGroups.dihedral(3, PointGroups.ReflectionAxis.HORIZONTAL));
  }
}
