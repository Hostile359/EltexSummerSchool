package ru.eltex;

import java.io.*;
import java.util.Scanner; 

public class Main{
    public static void main(String args[]){
        String path = "/proc/";
        File proc = new File(path);
        String[] dirs = proc.list();
        System.out.println();
        for(int i = 0; i < dirs.length; i++)
            System.out.println(dirs[i]);
        System.out.println();
        System.out.printf("PID    |NAME\n");
        for(int i = 0; i < dirs.length; i++) {
            try {
                Integer.parseInt(dirs[i]);
                try {
                    FileReader fr = new FileReader (path + dirs[i] + "/stat");
                    Scanner scan = new Scanner(fr);
                    scan.useDelimiter("\\(");
                    String name = scan.next();
                    scan.useDelimiter("\\) ");
                    name = scan.next();
                    name = name.substring(1);
                    System.out.printf("%-7s|%s\n",dirs[i], name);
                }
                catch (IOException error) {
                    System.out.println("Failed open file " + path+  dirs[i] + "/stat");
                    System.err.print(error.getMessage()); 
                }
            }    
            catch (NumberFormatException e) {
                continue;
            }
        }
    }
}
