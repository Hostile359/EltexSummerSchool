package ru.eltex;

import java.util.Scanner; 

public class Calc{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        System.out.print("Input first number: ");
        Double num1 = in.nextDouble();
        
        System.out.print("Input operation: ");
        Character op = in.next().charAt(0);
        
        System.out.print("Input second number: ");
        Double num2 = in.nextDouble();
        
        Double res = 0.0;
        
        switch(op) {
            case '+': { res = num1 + num2; } break;
            
            case '-': { res = num1 - num2; } break;
            
            case '*': { res = num1 * num2; } break;
            
            case '/': { 
                if(num2 != 0)
                    res = num1 / num2;
                else {
                    System.out.println("Error: divide by ZERO!!!");
                    return;
                }
            } break;
            
            default: { System.out.println("Error: Unknown operation"); return; }
        }
        
        System.out.println("Result = : " + res);

    }
}
