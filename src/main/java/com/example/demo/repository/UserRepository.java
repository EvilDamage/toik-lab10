package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {

    private final Map<Integer, User> usersDatabase;
    public enum Status {
        OK,
        FORBIDDEN,
        UNAUTHORIZED
    }

    public UserRepository() {
        usersDatabase = new HashMap<>();

        usersDatabase.put(1, new User("cracker", "cracker1234", true, 0));
        usersDatabase.put(2, new User("marry", "marietta!#09", true, 0));
        usersDatabase.put(3, new User("silver", "$silver$", true, 0));
    }

    public Status checkLogin(final String login, final String password) {
        for(Map.Entry<Integer, User> entry : usersDatabase.entrySet()){
            User user = entry.getValue();
            if(user.getLogin().equals(login)){
                if(!user.isActive()){
                    return Status.FORBIDDEN;
                }
                if(user.getPassword().equals(password)){
                    return Status.OK;
                }
                else{
                    user.setIncorrectLoginCounter(user.getIncorrectLoginCounter() + 1);
                    if(user.getIncorrectLoginCounter() >= 3){
                        user.setActive(false);
                    }
                }
            }
        }
        return Status.UNAUTHORIZED;
    }
}
