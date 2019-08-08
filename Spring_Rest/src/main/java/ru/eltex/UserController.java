package ru.eltex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository crudRep;

    @RequestMapping("/get_users")
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        crudRep.findAll().forEach(crud -> {users.add(crud);});
        //users.forEach(User::printInf);
        return users;
    }

    @RequestMapping("/get_user")
    public User getUser(@RequestParam("id") Integer id) {
        //System.out.println("ID   " + id);

        return crudRep.findById(id).get();
    }

    @RequestMapping("/get_user/{id}")
    public User getUser1(@PathVariable("id") Integer id) {
        //System.out.println("ID   " + id);

        return crudRep.findById(id).get();
    }
}

