package ru.eltex;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.io.*;
import java.util.*;

@SpringBootApplication
public class Main{



    public static void main(String args[]) {
        try {
            SpringApplication.run(Main.class, args);
        } catch (Exception error) {
            System.err.print(error.getMessage());
        }
    }

    @Bean // компонент контекста Spring
    public CommandLineRunner demo(UserRepository crudRepository, CallsRepository mongoRepository) throws Exception{

        ArrayList<User> users = new ArrayList<User>();

        //try {

        FileReader fr = new FileReader("files/users.csv");
        Scanner scan = new Scanner(fr);

        for (int j = 0; scan.hasNextLine(); ++j) {
            String input_str;
            input_str = scan.nextLine();
            System.out.println(input_str);
            User temp = new User();
            Integer check = temp.fromCSV(input_str);
            if (check == 0) {
                users.add(temp);
            } else
                System.out.println("Wrong format of string: " + temp);
        }
        fr.close();
        scan.close();
       /* } catch (IOException error) {
            System.out.println("Failed open file");
            System.err.print(error.getMessage());
        }*/
        ArrayList<Calls> calls = new ArrayList<Calls>();

        //try {

        fr = new FileReader("files/calls.csv");
        scan = new Scanner(fr);

        for (int j = 0; scan.hasNextLine(); ++j) {
            String input_str;
            input_str = scan.nextLine();
            System.out.println(input_str);
            Calls temp = new Calls();
            Integer check = temp.fromCSV(input_str);
            if (check == 0) {
                calls.add(temp);
            } else
                System.out.println("Wrong format of string: " + temp);
        }
        fr.close();
        scan.close();
        return (args) -> {
            users.forEach(user -> {
                user.printInf();
                crudRepository.save(user);
            });

            calls.forEach(call -> {
                call.printInf();
                mongoRepository.save(call);
            });
        };
    }
}
