package ru.eltex;

import org.codehaus.jackson.map.*;
import java.io.*;
import java.util.Scanner; 

public class Main{
    public static void main(String args[]){
        User u1 = new User(1, "Fas", "+81234", "aaa@mail.com");
        User u2 = new User();
        try {
            FileWriter fw = new FileWriter ("files/user.json");
            String temp_s = u1.toJSON();
            if(temp_s != null) {
                System.out.println(temp_s);
                u1.printInf();
                fw.write(temp_s);
            }
            fw.close();    
        }
        catch (IOException error) {
            System.out.println("Failed open file");
            System.err.print(error.getMessage()); 
        }
        
        try {
            FileReader fr = new FileReader ("files/user.json");
            Scanner scan = new Scanner(fr);
            String input_str = scan.nextLine();
            System.out.println(input_str);
            User temp_u = new User();
            Integer check = temp_u.fromJSON(input_str);
            if(check == 0) {
                u2 = temp_u;
                u2.setId("2");
                u2.printInf();
            }else
                System.out.println("Wrong format of string: " + temp_u);
            System.out.println();
                        
        }
        catch (IOException error) {
            System.out.println("Failed open file");
            System.err.print(error.getMessage()); 
        }
    }
}
