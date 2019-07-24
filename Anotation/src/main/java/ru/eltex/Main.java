package ru.eltex;

import java.lang.reflect.*;

public class Main{
    public static void main(String args[]){
        User u1 = new User();
        
        Integer massa = 0;
        Integer age = 0;
        Integer sex = 0;
        try {
            Class cl = u1.getClass();
            People pl = (People) cl.getAnnotation(People.class);
            massa = pl.massa();
            age = pl.age();
            sex = pl.sex();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        System.out.println("User massa: " + massa);
        System.out.println("User age: " + age);
        System.out.println("User sex(1-male; 2-female): " + sex);
    }
}
