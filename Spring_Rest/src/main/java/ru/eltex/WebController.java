package ru.eltex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WebController {

    @Autowired
    private UserRepository crudRep;

    @RequestMapping("/")
    public String users(Model model) {
        /*List<User> users = new ArrayList<>();
        crudRep.findAll().forEach(crud -> {users.add(crud);});
        users.forEach(User::printInf);*/
        model.addAttribute("users", crudRep.findAll());

        return "users";
    }

    @RequestMapping("/admin")
    public String admin(Model model) {
        /*List<User> users = new ArrayList<>();
        crudRep.findAll().forEach(crud -> {users.add(crud);});
        users.forEach(User::printInf);*/
        model.addAttribute("users", crudRep.findAll());

        return "admin";
    }

    
}
