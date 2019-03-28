/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icosilune.crystals.math.pointgroups;

import static com.icosilune.crystals.math.CrystalSystemType.CUBIC;
import static com.icosilune.crystals.math.CrystalSystemType.HEXAGONAL;
import static com.icosilune.crystals.math.CrystalSystemType.MONOCLINIC;
import static com.icosilune.crystals.math.CrystalSystemType.ORTHORHOMBIC;
import static com.icosilune.crystals.math.CrystalSystemType.TETRAGONAL;
import static com.icosilune.crystals.math.CrystalSystemType.TRICLINIC;
import static com.icosilune.crystals.math.CrystalSystemType.TRIGONAL;
import static com.icosilune.crystals.math.pointgroups.PointGroups.cyclic;
import static com.icosilune.crystals.math.pointgroups.PointGroups.mirror;
import static com.icosilune.crystals.math.pointgroups.PointGroups.dihedral;
import static com.icosilune.crystals.math.pointgroups.PointGroups.ReflectionAxis.DIAGONAL;
import static com.icosilune.crystals.math.pointgroups.PointGroups.ReflectionAxis.HORIZONTAL;
import static com.icosilune.crystals.math.pointgroups.PointGroups.ReflectionAxis.NONE;
import static com.icosilune.crystals.math.pointgroups.PointGroups.ReflectionAxis.VERTICAL;

import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;
import com.icosilune.crystals.math.CrystalSystemType;
import com.icosilune.crystals.math.Matrix3d;
import com.icosilune.crystals.math.pointgroups.PointGroups.ReflectionAxis;

/**
 *
 * @author ashmore
 */
public enum CrystalSystem {
  PEDIAL(TRICLINIC, "Pedial", "C1", new PointGroup(cyclic(1, NONE))),
  PINACOIDAL(TRICLINIC, "Pinacoidal", "S2", new PointGroup(mirror(2))),
  
  SPHENODIAL(MONOCLINIC, "Sphenoidal", "C2", new PointGroup(cyclic(2, NONE))),
  DOMATIC(MONOCLINIC, "Domatic", "C1h", new PointGroup(cyclic(1, HORIZONTAL))),
  PRISMATIC(MONOCLINIC, "Prismatic", "C2h", new PointGroup(cyclic(2, HORIZONTAL))),
  
  RHOMIBIC_DISPHENODIAL(ORTHORHOMBIC, "Rhombic-disphenoidal", "D2", new PointGroup(dihedral(2, NONE))),
  RHOMIBIC_PYRAMIDAL(ORTHORHOMBIC, "Rhombic-pyramidal", "C2v", new PointGroup(cyclic(2, VERTICAL))),
  RHOMIBIC_DIPYRAMIDAL(ORTHORHOMBIC, "Rhombic-dipyramidal", "D2h", new PointGroup(dihedral(2, HORIZONTAL))),
  
  TETRAGONAL_PYRAMIDAL(TETRAGONAL, "Tetragonal-pyramidal", "C4", new PointGroup(cyclic(4, NONE))),
  TETRAGONAL_DISPHENODIAL(TETRAGONAL, "Tetragonal-disphenodial", "S4", new PointGroup(mirror(4))),
  TETRAGONAL_DIPYRAMIDAL(TETRAGONAL, "Tetragonal-dipyramidal", "C4h", new PointGroup(cyclic(4, HORIZONTAL))),
  TETRAGONAL_TRAPEZOHEDRAL(TETRAGONAL, "Tetragonal-trapezohedral", "D4", new PointGroup(dihedral(4, NONE))),
  DITETRAGONAL_PYRAMIDAL(TETRAGONAL, "Dietragonal-pyramidal", "C4v", new PointGroup(cyclic(4, VERTICAL))),
  TETRAGONAL_SCALENOHEDRAL(TETRAGONAL, "Tetragonal-scalenohedral", "D2d", new PointGroup(dihedral(2, DIAGONAL))),
  DITETRAGONAL_DIPYRAMIDAL(TETRAGONAL, "Dietragonal-dipyramidal", "D4h", new PointGroup(dihedral(4, HORIZONTAL))),
  
  TRIGONAL_PYRAMIDAL(TRIGONAL, "Trigonal-pyramidal", "C3", new PointGroup(cyclic(3, NONE))),
  RHOMBOHEDRAL(TRIGONAL, "Rhombohedral", "S6", new PointGroup(mirror(6))),
  TRIGONAL_TRAPEZOHEDRAL(TRIGONAL, "Trigonal-trapezohedral", "D3", new PointGroup(dihedral(3, NONE))),
  DITRIGONAL_PYRAMIDAL(TRIGONAL, "Ditrigonal-pyramidal", "C3v", new PointGroup(cyclic(3, VERTICAL))),
  DITRIGONAL_SCALENOHEDRAL(TRIGONAL, "Dirigonal-scalenohedral", "D3d", new PointGroup(dihedral(3, DIAGONAL))),
  
  HEXAGONAL_PYRAMIDAL(HEXAGONAL, "Hexagonal-pyramidal", "C6", new PointGroup(cyclic(6, NONE))),
  TRIGONAL_DIPYRAMIDAL(HEXAGONAL, "Trigonal-dipyramidal", "C3h", new PointGroup(cyclic(3, HORIZONTAL))),
  HEXAGONAL_DIPYRAMIDAL(HEXAGONAL, "Hexagonal-dipyramidal", "C6h", new PointGroup(cyclic(6, HORIZONTAL))),
  HEXAGONAL_TRAPEZOHEDRAL(HEXAGONAL, "Hexagonal-trapezohedral", "C6v", new PointGroup(cyclic(6, VERTICAL))),
  DITRIGONAL_DIPYRAMIDAL(HEXAGONAL, "Ditrigonal-dipyramidal", "D3h", new PointGroup(dihedral(3, HORIZONTAL))),
  DIHEXAGONAL_DIPYRAMIDAL(HEXAGONAL, "Diexagonal-dipyramidal", "D6h", new PointGroup(dihedral(6, HORIZONTAL))),
  
  // ... cubic ones
  ;
  
  private final CrystalSystemType systemType;
  private final String crystalClassName;
  private final String shonfilesNotation;
  private final PointGroup pointGroup;
  
  CrystalSystem(CrystalSystemType systemType, String className, String shonfilesNotation, PointGroup pointGroup) {
    this.systemType = systemType;
    this.crystalClassName = className;
    this.shonfilesNotation = shonfilesNotation;
    this.pointGroup = pointGroup;
  }

  public CrystalSystemType getCrystalSystemType() {return systemType;}
  public String getCrystalClassName() {return crystalClassName;}
  public String getShonfilesNotation() {return shonfilesNotation;}
  public PointGroup getPointGroup() {return pointGroup;}
}
