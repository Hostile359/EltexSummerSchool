package ru.eltex;

import java.util.ArrayList;
import java.io.*;
import java.util.Scanner; 

public class Main{
    public static void main(String args[]){
        Integer quit = 0;
        while (quit == 0) {
            System.out.println("1-Developers info");
            System.out.println("2-Managers info");
            System.out.println("3-Sales info");
            System.out.println("q-Exit");
            System.out.println("Enter 1,2,3 or q: ");
            Scanner in = new Scanner(System.in);
            Character op = in.next().charAt(0);
            
            switch(op) {
                case '1': {
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
                } break;
                
                case '2': {
                
                    ArrayList<Manager> man = new ArrayList<Manager>();
                    
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
                        FileWriter fw = new FileWriter ("files/man_output.csv");
                        for (int i = 0; i < man.size(); ++i) {
                            fw.write(man.get(i).toCSV() + "\n");
                        }
                        fw.close();
                    }
                    catch (IOException error) {
                        System.err.print(error.getMessage()); 
                    }
                } break;
                
                
                case '3': {
                    
                    ArrayList<Sales> sal = new ArrayList<Sales>();
                    
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
                        FileWriter fw = new FileWriter ("files/sales_output.csv");
                        for (int i = 0; i < sal.size(); ++i) {
                            fw.write(sal.get(i).toCSV() + "\n");
                        }
                        fw.close();
                    }
                    catch (IOException error) {
                        System.err.print(error.getMessage()); 
                    }
                } break;
                
                case 'q': { quit = 1; }
            }
        }
    }
}
