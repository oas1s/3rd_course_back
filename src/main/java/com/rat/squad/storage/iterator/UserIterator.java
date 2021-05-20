package com.rat.squad.storage.iterator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * UserIterator class implementing Iterator
 * 2 methods overridden: hasNext() and next()
 * hasNext() returns bool var is Collection having next param
 * next() returns next Collection item
 */
public class UserIterator implements Iterator<User> {

    Collection<User> users;
    List<User> usersArr;
    private int current = 0;

    UserIterator(Collection<User> users){
        this.users = users;
        this.usersArr = new ArrayList<>(users);
    }

    @Override
    public boolean hasNext() {
        return current < users.size();
    }

    @Override
    public User next() {
        return usersArr.get(current++);
    }
}
