package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {

    private final Map<Integer, User> usersDatabase;

    public UserRepository() {
        usersDatabase = new HashMap<>();

        usersDatabase.put(1, new User("cracker", "cracker1234", true, 0));
        usersDatabase.put(2, new User("marry", "marietta!#09", true, 0));
        usersDatabase.put(3, new User("silver", "$silver$", true, 0));
    }

    public ResponseEntity<Void> checkLogin(final String login, final String password) {
        for(Map.Entry<Integer, User> entry : usersDatabase.entrySet()){
            User user = entry.getValue();
            if(user.getLogin().equals(login)){
                if(!user.isActive()){
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
                }
                if(user.getPassword().equals(password)){
                    return new ResponseEntity<>(HttpStatus.OK);
                }
                else{
                    user.setIncorrectLoginCounter(user.getIncorrectLoginCounter() + 1);
                    if(user.getIncorrectLoginCounter() >= 3){
                        user.setActive(false);
                    }
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
