package com.rat.squad.storage.iterator;

import lombok.*;


/**
 * User Class containing name surname age credits and gender fields
 * Gender Is Enum containing male and female params
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User {
    private String name;
    private String surname;
    private Integer age;
    private Double credits;
    private gender gender;

    enum gender {
        male,
        female
    }
}


