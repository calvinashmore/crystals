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
interface Linear<T extends Linear<T>> {

  /** Returns the sum of this and other. */
  T add(T other);

  /** Returns the scalar product of this with other. */
  T multiply(double other);

  /** Returns the norm of this. */
  double norm();

  /** Returns the difference of this and other. */
  default T subtract(T other) {
    return add(other.multiply(-1));
  }

  /** Returns the normalized value of this. Does not protect against division by zero. */
  default T normalize() {
    return multiply(1.0 / norm());
  }
}
