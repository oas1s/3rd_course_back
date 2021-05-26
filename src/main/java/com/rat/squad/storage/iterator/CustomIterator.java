package com.rat.squad.storage.iterator;

/**
 * @param <T>
 * Custom Iterator class providing basic operations to Iterate classes
 * hasNext() abstract method returns if Collection has next iterable unit
 * next() method returns next iterable unit
 */
public interface CustomIterator<T> {

    boolean hasNext();

    T next();
}
