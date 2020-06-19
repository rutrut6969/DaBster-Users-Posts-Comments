package com.dabster.dabusers.users.repo;

import com.dabster.dabusers.users.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsersRepo extends CrudRepository<User, Long> {

    User findByUsername(String name);

    List<User> findByUsernameContainingIgnoreCase(String name);

}
