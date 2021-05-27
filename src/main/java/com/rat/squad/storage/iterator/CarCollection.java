package com.rat.squad.storage.iterator;

import java.util.LinkedList;
import java.util.List;

/**
 * CarCollection коллекция для хранения машин
 * Как внутренняя реализация был использован LinkedList
 * реализован метод iterator() который возврашает итератор машин
 */
public class CarCollection {

    private List<Car> carList = new LinkedList<>();

    public boolean add(Car car) {
        return carList.add(car);
    }

    public boolean remove(Car car) {
        return carList.remove(car);
    }

    public int size() {
        return carList.size();
    }

    public Car get(int i){
        return carList.get(i);
    }

    public CustomIterator<Car> iterator() {
        return new CarIterator(carList);
    }
}
