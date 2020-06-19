package com.dabster.dabusers.users.controller;


import com.dabster.dabusers.users.User;
import com.dabster.dabusers.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Here will be your endpoints for the Users Table CRUD Functionality endpoints!
        // C Create --
           // Utilize your creation methods for this endpoint(s)
            @PostMapping(value = "/signup", consumes = {"application/json"})
            public ResponseEntity<?> createUser (@Validated @RequestBody User user) throws URISyntaxException {
                user.setUserid(0);
                user = userService.save(user);

                // Settiing up the headers and URI
                HttpHeaders responseHeaders = new HttpHeaders();
                URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/api/users/{userid}")
                        .buildAndExpand(user.getUserid())
                        .toUri();
                responseHeaders.setLocation(newUserURI);
                return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
            }

        // R Read --
            // Utilize your readability functions here.

            // Find all users
            @GetMapping(value = "/", produces = {"application/json"})
            public ResponseEntity<?> findAllUsers(){
                List<User> allUsers = userService.findAllUsers();
                return new ResponseEntity<>(allUsers, HttpStatus.OK);
            }

            // Find User By Id
            @GetMapping(value = "/user/{userid}", produces = {"application/json"})
            public ResponseEntity<?> findUserByID(@PathVariable long userid) throws Throwable{
                User foundUser = userService.findUserById(userid);
                return new ResponseEntity<>(foundUser, HttpStatus.OK);
            }

            @GetMapping(value = "/username/{username}", produces = {"application/json"})
            public ResponseEntity<?> findUserByName(@PathVariable String username){
                User user = userService.findUserByName(username);
                return new ResponseEntity<>(user, HttpStatus.OK);
            }

            @GetMapping(value = "/likename/{likename}", produces = {"application/json"})
            public ResponseEntity<?> findUsersByLikename(@PathVariable String likename){
                List<User> foundUsers = userService.findAllByNameContaining(likename);
                return new ResponseEntity<>(foundUsers, HttpStatus.OK);
            }

            // U Update --
                @PutMapping(value = "/user/{userid}", consumes = {"application/json"})
                public ResponseEntity<?> updateUser(@PathVariable long userid, User userInfo) {
                    userService.updateUserInfo(userInfo, userid);
                    return new ResponseEntity<>(null, HttpStatus.OK);
                }

            // D Delete --
                @DeleteMapping(value = "/user/{userid}")
                public ResponseEntity<?> deleteUser(@PathVariable long userid){
                    userService.deleteUser(userid);
                    return new ResponseEntity<>(null, HttpStatus.OK);
                }


}
