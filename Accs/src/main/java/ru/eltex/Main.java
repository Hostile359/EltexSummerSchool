package ru.eltex;

import java.util.ArrayList;
import java.io.*;
import java.util.Properties;
import java.util.Scanner;
import java.sql.*; 

public class Main{

    private static String DB_URL;
    private static String username;
    private static String password;
    
    public static void dev_to_SQL(ArrayList<Developer> devs) throws SQLException{
        Connection connection = DriverManager.getConnection(DB_URL, username, password); //соединение с БД
        Statement statement = connection.createStatement();
        
        for (int j = 0; j < devs.size(); ++j) {
            Integer id = devs.get(j).getId();
            ResultSet check = statement.executeQuery("SELECT id FROM developer WHERE id=" + id + ";"); // получение записей
            if(!check.next()) {
                String fio = devs.get(j).getFio();
                String phone = devs.get(j).getPhone();
                String email = devs.get(j).getEmail();
                String langs = devs.get(j).getLang();
                String into = "(" + id + ", '" + fio + "', '" + phone + "', '" + email + "', '" + langs + "');";
                statement.executeUpdate("INSERT INTO developer VALUE" + into); // добавление/удаление/изменение записей
            }
        }
        
        
        connection.close(); // отключение от БД
    }
    
    public static void dev_from_SQL(ArrayList<Developer> devs) throws SQLException{
        Connection connection = DriverManager.getConnection(DB_URL, username, password); //соединение с БД
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM developer;"); // получение записей
        System.out.println("From SQL: ");
        Integer j = 0;
        while (resultSet.next()){ // проход по полученным записям
            Developer temp = new Developer();
            temp.setId(resultSet.getString("id"));
            temp.setFio(resultSet.getString("fio"));
            temp.setPhone(resultSet.getString("phone"));
            temp.setEmail(resultSet.getString("email"));
            temp.setLangs(resultSet.getString("languages"));
            temp.printInf();
            devs.add(temp);
        }
        System.out.println();
        connection.close(); // отключение от БД
    }
    
    public static void man_to_SQL(ArrayList<Manager> mans) throws SQLException{
        Connection connection = DriverManager.getConnection(DB_URL, username, password); //соединение с БД
        Statement statement = connection.createStatement();
        
        for (int j = 0; j < mans.size(); ++j) {
            Integer id = mans.get(j).getId();
            ResultSet check = statement.executeQuery("SELECT id FROM manager WHERE id=" + id + ";"); // получение записей
            if(!check.next()) {
                String fio = mans.get(j).getFio();
                String phone = mans.get(j).getPhone();
                String email = mans.get(j).getEmail();
                String sales = mans.get(j).getSales();
                String into = "(" + id + ", '" + fio + "', '" + phone + "', '" + email + "', '" + sales + "');";
                statement.executeUpdate("INSERT INTO manager VALUE" + into); // добавление/удаление/изменение записей
            }
        }
        
        
        connection.close(); // отключение от БД
    }
    
    public static void man_from_SQL(ArrayList<Manager> mans) throws SQLException{
        Connection connection = DriverManager.getConnection(DB_URL, username, password); //соединение с БД
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM manager;"); // получение записей
        System.out.println("From SQL: ");
        Integer j = 0;
        while (resultSet.next()){ // проход по полученным записям
            Manager temp = new Manager();
            temp.setId(resultSet.getString("id"));
            temp.setFio(resultSet.getString("fio"));
            temp.setPhone(resultSet.getString("phone"));
            temp.setEmail(resultSet.getString("email"));
            temp.setSales(resultSet.getString("sales"));
            temp.printInf();
            mans.add(temp);
        }
        System.out.println();
        connection.close(); // отключение от БД
    }
    
    public static void sal_to_SQL(ArrayList<Sales> sales) throws SQLException{
        Connection connection = DriverManager.getConnection(DB_URL, username, password); //соединение с БД
        Statement statement = connection.createStatement();
        
        for (int j = 0; j < sales.size(); ++j) {
            Integer id = sales.get(j).getId();
            ResultSet check = statement.executeQuery("SELECT id FROM sales WHERE id=" + id + ";"); // получение записей
            if(!check.next()) {
                String name = sales.get(j).getName();
                Integer price = sales.get(j).getPrice();
                String into = "(" + id + ", '" + name + "', '" + price + "');";
                statement.executeUpdate("INSERT INTO sales VALUE" + into); // добавление/удаление/изменение записей
            }
        }
        
        
        connection.close(); // отключение от БД
    }
    
    public static void sal_from_SQL(ArrayList<Sales> sales) throws SQLException{
        Connection connection = DriverManager.getConnection(DB_URL, username, password); //соединение с БД
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM sales;"); // получение записей
        System.out.println("From SQL: ");
        Integer j = 0;
        while (resultSet.next()){ // проход по полученным записям
            Sales temp = new Sales();
            temp.setId(resultSet.getString("id"));
            temp.setName(resultSet.getString("name"));
            temp.setPrice(resultSet.getString("price"));            
            temp.printInf();
            sales.add(temp);
        }
        System.out.println();
        connection.close(); // отключение от БД
    }
    
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

    public static void main(String args[]) {

        try {
            getProp("src/main/resources/db.properties");
        } catch (IOException e) {
            System.err.println("ERROR: properties file doesn't exist!");
            return;
        }
        Integer quit = 0;
        while (quit == 0) {
            System.out.println("\n\n1-Developers info");
            System.out.println("2-Managers info");
            System.out.println("3-Sales info");
            System.out.println("q-Exit");
            System.out.println("Enter 1,2,3 or q: ");
            Scanner in = new Scanner(System.in);
            Character op = in.next().charAt(0);
            
            switch(op) {
                case '1': {
                    ArrayList<Developer> devs = new ArrayList<Developer>();
                    ArrayList<Developer> devs_out = new ArrayList<Developer>();
                    
                    try {
                        FileReader fr = new FileReader ("files/dev.csv");
                        Scanner scan = new Scanner(fr);

                        for (int j = 0; scan.hasNextLine(); ++j) {
                            String input_str;
                            input_str = scan.nextLine();
                            System.out.println(input_str);
                            Developer temp = new Developer();
                            Integer check = temp.fromCSV(input_str);
                            if(check == 0) {
                                devs.add(temp);
                                devs.get(j).printInf();
                            }else
                                System.out.println("Wrong format of string: " + temp);
                        }
                        System.out.println();
                        
                    }
                    catch (IOException error) {
                        System.out.println("Failed open file");
                        System.err.print(error.getMessage()); 
                    }
                    
                    try {
                        dev_to_SQL(devs);
                        dev_from_SQL(devs_out);
                        
                        try {
                            FileWriter fw = new FileWriter ("files/dev_output.csv");
                            for (int i = 0; i < devs_out.size(); ++i) {
                                fw.write(devs_out.get(i).toCSV() + "\n");
                            }
                            fw.close();
                        } catch (IOException error) {
                            System.err.print(error.getMessage()); 
                        }
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    
                } break;
                
                case '2': {
                
                    ArrayList<Manager> man = new ArrayList<Manager>();
                    ArrayList<Manager> man_out = new ArrayList<Manager>();
                    
                    try {
                        FileReader fr = new FileReader ("files/man.csv");
                        Scanner scan = new Scanner(fr);

                        for (int j = 0; scan.hasNextLine(); ++j) {
                             String input_str;
                            input_str = scan.nextLine();
                            System.out.println(input_str);
                            Manager temp = new Manager();
                            Integer check = temp.fromCSV(input_str);
                            if(check == 0) {
                                man.add(temp);
                                man.get(j).printInf();
                            }else
                                System.out.println("Wrong format of string: " + temp);
                        }
                        System.out.println();
                        
                    }
                    catch (IOException error) {
                        System.out.println("Failed open file");
                        System.err.print(error.getMessage()); 
                    }
                    
                    try {
                        man_to_SQL(man);
                        man_from_SQL(man_out);
                        
                        try {
                            FileWriter fw = new FileWriter ("files/man_output.csv");
                            for (int i = 0; i < man_out.size(); ++i) {
                                fw.write(man_out.get(i).toCSV() + "\n");
                            }
                            fw.close();
                        } catch (IOException error) {
                            System.err.print(error.getMessage()); 
                        }
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    
                    
                } break;
                
                
                case '3': {
                    
                    ArrayList<Sales> sal = new ArrayList<Sales>();
                    ArrayList<Sales> sal_out = new ArrayList<Sales>();
                    
                    try {
                        FileReader fr = new FileReader ("files/sales.csv");
                        Scanner scan = new Scanner(fr);

                        for (int j = 0; scan.hasNextLine(); ++j) {
                             String input_str;
                            input_str = scan.nextLine();
                            System.out.println(input_str);
                            Sales temp = new Sales();
                            Integer check = temp.fromCSV(input_str);
                            if(check == 0) {
                                sal.add(temp);
                                sal.get(j).printInf();
                            }else
                                System.out.println("Wrong format of string: " + temp);
                        }
                        System.out.println();
                        
                    }
                    catch (IOException error) {
                        System.out.println("Failed open file");
                        System.err.print(error.getMessage()); 
                    }
                    
                    try {
                        sal_to_SQL(sal);
                        sal_from_SQL(sal_out);
                        try {
                            FileWriter fw = new FileWriter ("files/sales_output.csv");
                            for (int i = 0; i < sal_out.size(); ++i) {
                                fw.write(sal_out.get(i).toCSV() + "\n");
                            }
                            fw.close();
                        } catch (IOException error) {
                            System.err.print(error.getMessage()); 
                        }
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    
                } break;
                
                case 'q': { quit = 1; }
            }
        }
    }
}
