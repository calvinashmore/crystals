/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icosilune.crystals.math.pointgroups;

import com.icosilune.crystals.math.Group;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.ImmutableGraph;
import com.google.common.graph.MutableGraph;
import com.icosilune.crystals.math.Matrix3d;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;

/**
 *
 * @author ashmore
 */
public class GraphGroup<T> implements Group<T> {
  
  private final T identity;
  private final ImmutableList<T> entries; // list so we can have an indexing on the elements
  private final BiFunction<T,T,T> operator;
  private final ImmutableMap<T,T> inverses;
  private final ImmutableGraph<T> baseGraph;

  public GraphGroup(Set<T> entries, BiFunction<T,T,T> operator) {
    this.identity = entries.iterator().next();
    this.entries = ImmutableList.copyOf(entries);
    this.operator = operator;
    this.inverses = findInverses(identity, entries, operator);
    
    Preconditions.checkArgument(checkClosure(entries, operator));
    
    for(T a : entries) {
      Preconditions.checkArgument(inverses.containsKey(a), "%s has no inverse", a);
    }
    
    Preconditions.checkArgument(inverses.get(identity).equals(identity), "Incorrect identity");
    
    MutableGraph<T> graph = GraphBuilder.<T>directed()
        .allowsSelfLoops(true)
        .build();
    for(T a : entries) {
      graph.addNode(a);
    }
    for(T a : entries) {
      for(T b : entries) {
        graph.putEdge(a, b);
      }
    }
    baseGraph = ImmutableGraph.copyOf(graph);
  }
  
  private ImmutableMap<T,T> findInverses(T identity, Set<T> entries, BiFunction<T,T,T> operator) {
    ImmutableMap.Builder<T,T> builder = ImmutableMap.builder();
    for(T a : entries)
      for(T b :entries)
        if (operator.apply(a, b).equals(identity))
          builder.put(a, b);
    return builder.build();
  }
  
  private boolean checkClosure(Set<T> group, BiFunction<T,T,T> operator) {
    for(T a : group)
      for(T b : group)
        if(!group.contains(operator.apply(a, b)))
          return false;
    return true;
  }
  
  /**
   * returns cycles within this graph
   */
  public Set<Set<T>> getCycles() {
    
    Set<Set<T>> subgroups = new HashSet<>();
    
    for (T generator : entries) {
      Set<T> subgroup = new HashSet<>();
      subgroup.add(generator);
      
      T x = generator;
      
      while (!x.equals(identity)) {
        x = operator.apply(generator, x);
        subgroup.add(x);
      }
      
      subgroups.add(ImmutableSet.copyOf(subgroup));
    }
    return ImmutableSet.copyOf(subgroups);
  }

  @Override
  public T identity() {
    return identity;
  }

  @Override
  public T apply(T a, T b) {
    return operator.apply(a, b);
  }

  @Override
  public T inverse(T a) {
    return inverses.get(a);
  }

  @Override
  public ImmutableList<T> entries() {
    return entries;
  }

  public ImmutableGraph<T> getBaseGraph() {
    return baseGraph;
  }
}
