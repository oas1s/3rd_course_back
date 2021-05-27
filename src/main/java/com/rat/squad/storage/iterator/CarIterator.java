package com.rat.squad.storage.iterator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * CarIterator реализует наш кастомный итератор
 * 2 метода унаследованы: hasNext() и next()
 * hasNext() возвращает есть ли у нас еще машины в коллекции
 * next() возвращает следующий элемент коллекции
 */
public class CarIterator implements CustomIterator<Car> {

    Collection<Car> cars;
    List<Car> carsArr;
    private int current = 0;

    CarIterator(Collection<Car> cars){
        this.cars = cars;
        this.carsArr = new ArrayList<>(cars);
    }

    @Override
    public boolean hasNext() {
        return current < cars.size();
    }

    @Override
    public Car next() {
        return carsArr.get(current++);
    }
}
