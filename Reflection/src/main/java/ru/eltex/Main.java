package ru.eltex;

import java.lang.reflect.*;

public class Main{
    public static void main(String args[]){
        User u1 = new User(1, "Fas", "+81234");
        
        Integer id = 0;
        String fio = "";
        String phone = "";
        try {
            Field field = u1.getClass().getDeclaredField("id");
            field.setAccessible(true);
            id = (Integer) field.get(u1);
            
            field = u1.getClass().getDeclaredField("fio");
            field.setAccessible(true);
            fio = (String) field.get(u1);
            
            field = u1.getClass().getDeclaredField("phone");
            field.setAccessible(true);
            phone = (String) field.get(u1);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("User id: " + id);
        System.out.println("User fio: " + fio);
        System.out.println("User phone: " + phone);
    }
}
