package ru.eltex;



import java.io.*;

@People
class User{
    private Integer id;
    private String fio;
    private String phone;
    
    User() {}
    
    User(Integer id, String fio, String phone) {
        this.id = id;
        this.fio = fio;
        this.phone = phone;
    }
}
