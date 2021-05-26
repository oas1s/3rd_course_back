package com.rat.squad.storage.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * UserCollection made for containing Users
 * As implementation inside was used List Collection
 * implemented iterator() method which returns UserIterator
 */
public class UserCollection {

    private List<User> userList = new ArrayList<>();

    public boolean add(User user) {
        return userList.add(user);
    }

    public boolean remove(User user) {
        return userList.remove(user);
    }

    public int size() {
        return userList.size();
    }

    public CustomIterator<User> iterator() {
        return new UserIterator(userList);
    }
}
