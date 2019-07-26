package ru.eltex;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Developer extends User{
    @ManyToMany(cascade = { CascadeType.ALL })
    @ElementCollection @CollectionTable(name="Languages")
    @Getter @Setter private List<Languages> lang = null;
    Developer() { this.lang = new ArrayList<Languages>(); }

    public void printInf() {

        System.out.println("Dev id: " + this.id);
        System.out.println("Dev fio: " + this.fio);
        System.out.println("Dev phone: " + this.phone);
        System.out.println("Dev email: " + this.email);

        System.out.print("Dev langs: ");
        for(int i = 0; i < this.lang.size(); i++)
            System.out.print(this.lang.get(i).getName() + " ");
        System.out.println();
        System.out.println();
    }

    public String getStrLang() {
        String temp = "";
        for(int i = 0; i < this.lang.size(); i++)
            temp += this.lang.get(i).getName() + ">";
        temp = temp.substring(0, temp.length() - 1);

        return temp;
    }

    public String toCSV() {
        String temp = this.getStrLang();
        //for(int i = 0; i < this.lang.size(); i++)
        //temp += this.lang.get(i) + ">";
        //temp = temp.substring(0, temp.length() - 1);
        return Integer.toString(this.id) + ";" + this.fio + ";" + this.phone + ";" + this.email + ";" + temp + ";";
    }

    public Integer fromCSV(String str) {
        String [] arg = str.split(";");
        if(arg.length == 5) {
            setId (Integer.valueOf(arg [0]));
            setFio (arg [1]);
            setPhone (arg [2]);
            setEmail (arg [3]);
            setLangs(arg[4]);
        }else
            return -1;

        return 0;
    }

    public void setLangs (String arg) {
        String [] argg = arg.split(">");
        for(int i = 0; i < argg.length; i++)
            this.addLang(argg[i]);
    }

    public void addLang (String arg) {
        this.lang.forEach(l -> {
            if(arg.equals(l.getName()))
                return;
        });
        Languages temp = new Languages(lang.size(), arg);
        this.lang.add(temp);
    }

}