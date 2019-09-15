package com.example.aopdemo.dao;

import com.example.aopdemo.model.User;
import com.example.aopdemo.model.Users;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {
    private static Users list = new Users();

    static {
        list.getUserList().add(new User(1, "Naveen", "Kumar", "naveen@arrc.in"));
        list.getUserList().add(new User(2, "Dig", "Singh", "dig@arrc.in"));
        list.getUserList().add(new User(3, "Vaibhav", "Verma", "vaibhav@arrc.in"));
    }

    public Users getAllUsers() {
        return list;
    }

    public void addUser(User user) {
        list.getUserList().add(user);
    }
}
