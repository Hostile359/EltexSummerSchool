package ru.eltex;

import java.util.ArrayList;
import java.io.*;
import java.util.Properties;
import java.util.Scanner;
import java.sql.*; 

public class Main{

    private static final Integer N = 1000;
    private static final Double dt= 1e-9;

    private static String DB_URL;
    private static String username;
    private static String password;

    
    public static void getProp(String file) throws IOException{
        FileInputStream fis;
        Properties property = new Properties();
        fis = new FileInputStream(file);
        property.load(fis);
        DB_URL = property.getProperty("db.host");
        username = property.getProperty("db.user");
        password = property.getProperty("db.password");
        fis.close();
    }

    public static void createTable() throws SQLException{
        Connection connection = DriverManager.getConnection(DB_URL, username, password); //соединение с БД
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS users(id INTEGER(11) UNIQUE KEY)");
        connection.close(); // отключение от БД
    }

    public static void addTest() throws SQLException{
        System.out.printf("AutoCommit | false    | true\n");
        Connection connection = DriverManager.getConnection(DB_URL, username, password);
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM users where id >= 0");
        connection.setAutoCommit(false);

        long start = System.nanoTime();
        for (int i = 0; i < N; i++)  {
            statement.executeUpdate("INSERT INTO users VALUE (" + i + ");");
        }

        long end = System.nanoTime();
        Double res = (end - start) * dt;
        System.out.printf("ADD        | %-8.6f |" , res);
        connection.commit();

        connection.setAutoCommit(true);
        statement.executeUpdate("DELETE FROM users where id >= 0");
        start = System.nanoTime();
        for (int i = 0; i < N; i++)  {
            statement.executeUpdate("INSERT INTO users VALUE (" + i + ");");
        }
        end = System.nanoTime();
        res = (end - start) * dt;
        System.out.printf(" %hi.6f" , res);
        connection.close();
    }

    public static void main(String args[]) {

        try {
            getProp("src/main/resources/db.properties");
        } catch (IOException e) {
            System.err.println("ERROR: properties file doesn't exist!");
            return;
        }

        try {
            createTable();
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return;
        }

        try {
            addTest();
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return;
        }

    }
}
