package com.rat.squad.storage.iterator;

import lombok.*;

/**
 * Класс Машина содержит в себе информацию про год модель стоимость мощьность и бренд
 * Бренд сделан как Enum
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Car {
    private String modelName;
    private Integer ageOfCreated;
    private Double price;
    private Integer horsePower;
    private brand brand;

    public enum brand {
        BMW,
        AUDI,
        VOLKSWAGEN,
        MERCEDES
    }
}


