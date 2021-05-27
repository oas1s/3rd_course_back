package com.rat.squad.storage.iterator;

/**
 * @param <T>
 * Итератор который предоставляет базовые абстрактные методы для итерации классов
 * hasNext() метод возвращает булевую переменную о том, еть ли еще итерируемый объект
 * next() возвращаетс следующий итерируемый объект
 */
public interface CustomIterator<T> {

    boolean hasNext();

    T next();
}
