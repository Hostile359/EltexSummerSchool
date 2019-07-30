package ru.eltex;


import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

@SpringBootApplication
class Main {

    /*public static String make_request(String str, UserRepository crudRepository) throws Exception{

        str = str.substring(1);
        String [] tokens = str.split("/");
        String output = "";
        //System.out.println(tokens.length);
        //for(int i = 0; i < tokens.length; i++)
            //System.out.println("!!!! " + tokens[i]);
        if(tokens.length == 1 && tokens[0].equals("get_users")) {
            ObjectMapper mapper = new ObjectMapper();
            ArrayList<User> users = new ArrayList<>();
            crudRepository.findAll().forEach(crud -> {users.add(crud);});
            //users.add(new User(1, "Alex", "900"));
            //users.add(new User(2, "Max", "912"));
            //users.add(new User(3, "Sam", "834"));
            output = mapper.writeValueAsString(users);
        }else if (tokens.length == 2 && tokens[0].equals("get_user")) {
            Integer id = Integer.parseInt(tokens[1]);
            ObjectMapper mapper = new ObjectMapper();

            User u = crudRepository.findById(id).get();
            output = mapper.writeValueAsString(u);
        }else {
            output = "HTTP/1.1 404";
        }
        return output;
    }*/

    public static void main(String[] args) {
        try {
            SpringApplication.run(Main.class, args);
        } catch (Exception error) {
            System.err.print(error.getMessage());
        }


        /*try {
            //String output_ = "HTTP/1.0: 200 OK\n";
            //String output_request = "GET ";
            //String output_protocol = "HTTP/1.0.";
            //System.out.print(output);
            ServerSocket s = new ServerSocket(2490);
            System.out.print("\nServer started\n");
            while(true) {
                Socket client  = s.accept();
                new Thread(() -> {
                    try {
                        System.out.println("Started thread: " + Thread.currentThread().getName());
                        InputStream inStream = client.getInputStream();
                        Scanner in = new Scanner(inStream);
                        String client_request = in.nextLine();
                        System.out.println(client_request);
                        String [] tokens = client_request.split(" ");
                        String output = make_request(tokens[1]);
                        //System.out.println(tokens[1]);
                        //String [] tok = tokens[1].split("/");
                        //for(int i = 0; i < tok.length; i++)
                            //System.out.println(tok[i]);
                        output = "HTTP/1.1 200 OK\n\n" + output;
                        System.out.println(output);
                        OutputStream outStream = client.getOutputStream();
                        PrintWriter out = new PrintWriter(outStream);
                        out.write(output);
                        out.flush();
                        client.close();
                    } catch (Exception exx) {
                        System.err.println(exx.getMessage());
                    }
                }).start();
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }*/
    }

    @Bean // компонент контекста Spring
    public CommandLineRunner demo(UserRepository crudRepository) throws Exception{


        return (args) -> {
            ServerSocket s = new ServerSocket(2490);
            System.out.print("\nServer started\n");
            while(true) {
                Socket client = s.accept();
                new Thread(() -> {
                    try {
                        System.out.println("Started thread: " + Thread.currentThread().getName());
                        InputStream inStream = client.getInputStream();
                        Scanner in = new Scanner(inStream);
                        String client_request = in.nextLine();
                        System.out.println(client_request);
                        String[] tokens = client_request.split(" ");
                        //String output = make_request(tokens[1], crudRepository);

                        tokens[1] = tokens[1].substring(1);
                        String [] token = tokens[1].split("/");
                        String output = "";
                        for(int i = 0; i < token.length; i++)
                            System.out.println("!!!! " + token[i]);
                        //System.out.println(tokens.length);
                        //for(int i = 0; i < tokens.length; i++)
                        //System.out.println("!!!! " + tokens[i]);

                        if(token.length == 1 && token[0].equals("get_users")) {
                            ObjectMapper mapper = new ObjectMapper();
                            ArrayList<User> users = new ArrayList<>();
                            crudRepository.findAll().forEach(crud -> {users.add(crud);});
                            //users.add(new User(1, "Alex", "900"));
                            //users.add(new User(2, "Max", "912"));
                            //users.add(new User(3, "Sam", "834"));
                            output = mapper.writeValueAsString(users);
                            output = "HTTP/1.1 200 OK\n\n" + output;
                        }else if (token.length == 2 && token[0].equals("get_user")) {
                            Integer id = Integer.parseInt(token[1]);
                            ObjectMapper mapper = new ObjectMapper();
                            System.out.println("!!!!! " + id + token[0]);
                            User u = crudRepository.findById(id).get();
                            output = mapper.writeValueAsString(u);
                            output = "HTTP/1.1 200 OK\n\n" + output;
                        }else {
                            output = "HTTP/1.1 404";
                        }

                        //System.out.println(tokens[1]);
                        //String [] tok = tokens[1].split("/");
                        //for(int i = 0; i < tok.length; i++)
                        //System.out.println(tok[i]);

                        System.out.println(output);
                        OutputStream outStream = client.getOutputStream();
                        PrintWriter out = new PrintWriter(outStream);
                        out.write(output);
                        out.flush();
                        client.close();
                    } catch (Exception exx) {
                        System.err.println(exx.getMessage() + "EROROR");
                    }
                }).start();
            }
        };
    }
}

