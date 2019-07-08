package ru.eltex;

import java.util.ArrayList;
import java.io.*;
import java.util.Scanner; 

public class Main{
    public static void main(String args[]){
        
        ArrayList<Developer> devs = new ArrayList<Developer>();
        
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
			FileWriter fw = new FileWriter ("files/dev_output.csv");
			for (int i = 0; i < devs.size(); ++i) {
				fw.write(devs.get(i).toCSV() + "\n");
			}
			fw.close();
		}
		catch (IOException error) {
	     	System.err.print(error.getMessage()); 
        }
        
        

    }
}
