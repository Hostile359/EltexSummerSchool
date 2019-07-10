package ru.eltex;

import java.math.BigInteger;

public class Main{
    public static void main(String args[]){
        BigInteger a = new BigInteger("2");
        
        a = a.pow(1000);
        
        System.out.print("2^1000 = " + a);

    }
}
