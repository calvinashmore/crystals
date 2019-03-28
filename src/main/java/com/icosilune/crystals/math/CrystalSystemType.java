/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icosilune.crystals.math;

/**
 *
 * @author ashmore
 */
public enum CrystalSystemType {
  TRICLINIC(CrystalFamilyType.TRICLINIC),
  MONOCLINIC(CrystalFamilyType.MONOCLINIC),
  ORTHORHOMBIC(CrystalFamilyType.ORTHORHOMBIC),
  TETRAGONAL(CrystalFamilyType.TETRAGONAL),
  TRIGONAL(CrystalFamilyType.HEXAGONAL),
  HEXAGONAL(CrystalFamilyType.HEXAGONAL),
  CUBIC(CrystalFamilyType.CUBIC),
  ;
  private final CrystalFamilyType family;

  private CrystalSystemType(CrystalFamilyType family) {
    this.family = family;
  }

  public CrystalFamilyType getFamily() {
    return family;
  }
}
