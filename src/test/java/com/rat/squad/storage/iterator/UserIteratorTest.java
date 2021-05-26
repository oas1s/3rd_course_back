package com.rat.squad.storage.iterator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class UserIteratorTest {

    @Test
    public void testUsersIterator() {
        //Iterator testing method
        //Creating Users
        User user1 = User.builder().age(18).credits(12.0).gender(User.gender.male).name("Tape").surname("Inatore").build();
        User user2 = User.builder().age(38).credits(36.0).gender(User.gender.male).name("Ivan").surname("Ivanov").build();
        User user3 = User.builder().age(48).credits(46.0).gender(User.gender.male).name("Petr").surname("Petrov").build();
        User user4 = User.builder().age(58).credits(82.0).gender(User.gender.male).name("Michael").surname("Micky").build();
        User user5 = User.builder().age(18).credits(82.0).gender(User.gender.female).name("Kelly").surname("Gun").build();

        //Creating users List
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        userList.add(user5);

        // Creating iterator and logging Users
        CustomIterator<User> iterator = new UserIterator(userList);
        int count = 0;
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toString());
            count++;
        }
        // assert for users count
        Assertions.assertEquals(5,count);
    }

}