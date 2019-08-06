package ru.eltex;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter protected Integer id;
    @Getter @Setter protected String fio;
    @Getter @Setter protected String phone;

    User() {}

    User(Integer id, String fio, String phone) {
        this.fio = fio;
        this.phone = phone;
    }

    public void printInf() {

        System.out.println("User id: " + this.id);
        System.out.println("User fio: " + this.fio);
        System.out.println("User phone: " + this.phone);

        System.out.println();
        System.out.println();
    }

    public String toCSV() {

        return Integer.toString(this.id) + ";" + this.fio + ";" + this.phone + ";";
    }

    public Integer fromCSV(String str) {
        String [] arg = str.split(";");
        if(arg.length == 3) {
            setId (Integer.valueOf(arg [0]));
            setFio (arg [1]);
            setPhone (arg [2]);
        }else
            return -1;

        return 0;
    }
}