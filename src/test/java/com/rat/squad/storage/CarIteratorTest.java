package com.rat.squad.storage;

import com.rat.squad.storage.iterator.Car;
import com.rat.squad.storage.iterator.CarCollection;
import com.rat.squad.storage.iterator.CustomIterator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class CarIteratorTest {

    @Test
    public void testCarsIterator() {
        // Создание объектов машин
        Car Car1 = Car.builder().ageOfCreated(2000).brand(Car.brand.BMW).horsePower(300).modelName("E39").build();
        Car Car2 = Car.builder().ageOfCreated(2000).brand(Car.brand.AUDI).horsePower(300).modelName("A4").build();
        Car Car3 = Car.builder().ageOfCreated(2000).brand(Car.brand.MERCEDES).horsePower(300).modelName("C300").build();
        Car Car4 = Car.builder().ageOfCreated(2000).brand(Car.brand.VOLKSWAGEN).horsePower(300).modelName("POLO").build();
        Car Car5 = Car.builder().ageOfCreated(2000).brand(Car.brand.BMW).horsePower(300).modelName("E39").build();

        //Добавление машин в коллекцию машин
        CarCollection carList = new CarCollection();
        carList.add(Car1);
        carList.add(Car2);
        carList.add(Car3);
        carList.add(Car4);
        carList.add(Car5);

        // Создание итератора и итерирование
        CustomIterator<Car> iterator = carList.iterator();
        List<Car> carListIterator = new ArrayList<>();

        while (iterator.hasNext()) {
            carListIterator.add(iterator.next());
        }

        // проверка на то, что итерируемые машины и первоначальные машины идентичны
        for (int i = 0; i <carListIterator.size() ; i++) {
            Assertions.assertEquals(carListIterator.get(i),carList.get(i));
        }
    }

}