/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icosilune.crystals.math.pointgroups;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.icosilune.crystals.math.Matrix3d;
import java.util.Set;
import java.util.function.BiFunction;

/**
 *
 * @author ashmore
 */
public class PointGroup extends GraphGroup<Matrix3d>{

  public PointGroup(ImmutableSet<Matrix3d> transformations) {
    super(transformations, Matrix3d::apply);
  }
}
