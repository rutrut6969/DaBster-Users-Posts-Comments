package com.dabster.dabusers.users.services;

import com.dabster.dabusers.users.User;

import java.util.List;

public interface UserService {
    // Inside of this file, you need to initiate all of your CRUD functionality:
    // C Create --
    User save(User user);
    // This method will create a new user upone request of the endpoint /users/signup


    // R Read --
    // this is where you will list all of your find methods:
    // Lists First for organization matters:

    List<User> findAllUsers();

    List<User> findAllByNameContaining(String name);

    // Now for the ones that return just a single user:
    User findUserById(long id) throws Throwable;

    User findUserByName(String name);


    // U Update --
    // This will be where you initiate your update method to update the User information:
    User updateUserInfo(User user, long id);

    // D Delete --
    // This method will be used to delete/remove users from the database:
    void deleteUser(long id);


}
