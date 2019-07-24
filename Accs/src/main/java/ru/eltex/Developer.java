package ru.eltex;

import java.util.Scanner; 
import java.util.ArrayList;

public class Developer extends User{
    private ArrayList<String> lang = null;
    Developer() { this.lang = new ArrayList<String>(); }
    
    public void printInf() {
        
        System.out.println("Dev id: " + this.id);
        System.out.println("Dev fio: " + this.fio);
        System.out.println("Dev phone: " + this.phone);
        System.out.println("Dev email: " + this.email);
		
        System.out.print("Dev langs: ");
        for(int i = 0; i < this.lang.size(); i++)
            System.out.print(this.lang.get(i) + " ");
        System.out.println();
        System.out.println();
    }
    
    public String getLang() {
        String temp = "";
        for(int i = 0; i < this.lang.size(); i++)
            temp += this.lang.get(i) + ">";
        temp = temp.substring(0, temp.length() - 1);
        
        return temp;
    }
    
	public String toCSV() {
        String temp = this.getLang();
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
            String [] argg = arg[4].split(">");
            for(int i = 0; i < argg.length; i++)
                this.addLang(argg[i]);
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

        this.lang.add(arg);
	}
    
    public void removeLang (String arg) {

        this.lang.remove(arg);
	}
        
}
