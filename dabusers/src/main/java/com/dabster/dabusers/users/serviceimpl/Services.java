package com.dabster.dabusers.users.serviceimpl;

import com.dabster.dabusers.users.User;
import com.dabster.dabusers.users.repo.UsersRepo;
import com.dabster.dabusers.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "userService")
public class Services implements UserService {

    // Auto Wiring The Repo(s)
    @Autowired
    private UsersRepo userrepo;


    @Transactional
    @Override
    public User save(User user) {
        User newUser = new User();

        newUser.setName(user.getName().toLowerCase());
        newUser.setUsername(user.getUsername());
        newUser.setBio(user.getBio());
        newUser.setEmail(user.getEmail().toLowerCase());
        newUser.setPassword(user.getPassword());

        // Add Implementation of Posts and comments here when they are created



        userrepo.save(newUser);
        return newUser;
    }

    @Override
    public List<User> findAllUsers() {

        List<User> foundUsers = new ArrayList<>();
        userrepo.findAll().iterator().forEachRemaining(foundUsers::add);
        return foundUsers;
    }

    @Override
    public List<User> findAllByNameContaining(String name) {
        return userrepo.findByUsernameContainingIgnoreCase(name.toLowerCase());
    }

    @Override
    public  User findUserById(long id) throws EntityNotFoundException {
        return userrepo.findById(id).orElseThrow(() -> new EntityNotFoundException("User with the ID of " + id + " Was not found"));
    }

    @Override
    public User findUserByName(String name) {
        User u = userrepo.findByUsername(name.toLowerCase());
        if (u == null){
            throw new EntityNotFoundException("User " + name + " Does not Exist!");
        }
        return u;
    }


    @Transactional
    @Override
    public User updateUserInfo(User user, long id) {
        User userToUpdate = findUserById(id);
        if(user.getName() != null){
            userToUpdate.setName(user.getName());
        }
        if(user.getBio() != null){
            userToUpdate.setBio(user.getBio());
        }
        if(user.getEmail() != null){
            userToUpdate.setEmail(user.getEmail());
        }
        if(user.getPassword() != null){
            userToUpdate.setPassword(user.getPassword());
        }

        return userrepo.save(userToUpdate);
    }

    @Transactional
    @Override
    public void deleteUser(long id) {
        User userToDelete = findUserById(id);
        userrepo.delete(userToDelete);

    }
}
