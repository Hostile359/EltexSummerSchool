package ru.eltex;

import java.util.Scanner; 
import java.util.ArrayList;

public class Manager extends User{
    private ArrayList<Integer> sale_id = null;
    private ArrayList<Integer> count_of_sales = null;
    Manager() { 
		this.sale_id = new ArrayList<Integer>();
		this.count_of_sales = new ArrayList<Integer>();
	}
    
    public void printInf() {
        
        System.out.println("Manager id: " + this.id);
        System.out.println("Manager fio: " + this.fio);
        System.out.println("Manager phone: " + this.phone);
        System.out.println("Manager email: " + this.email);
		
        System.out.print("Manager sales: ");
        for(int i = 0; i < this.sale_id.size(); i++)
            System.out.print("id of goods(" + Integer.toString(this.sale_id.get(i)) 
                + ") count:" + Integer.toString(this.count_of_sales.get(i)) + "; ");
                
        System.out.println();
        System.out.println();
    }

	public String toCSV() {
        String temp = "";
        for(int i = 0; i < this.sale_id.size(); i++)
            temp += Integer.toString(this.sale_id.get(i)) + "="
                + Integer.toString(this.count_of_sales.get(i)) + ">";
                
        temp = temp.substring(0, temp.length() - 1);
		return Integer.toString(this.id) + ";" + this.fio + ";" + this.phone + ";" + this.email + ";" + temp + ";";
	}

	public Integer fromCSV(String str) {
		String [] arg = str.split(";");
        if(arg.length == 5) {
            setId (arg [0]);
            setFio (arg [1]);
            setPhone (arg [2]);
            setEmail (arg [3]);
            String [] argg = arg[4].split(">");
            for(int i = 0; i < argg.length; i++)
                this.addSales(argg[i]);
        }else 
            return -1;
        
        return 0;
    }
    
    public void addSales (String arg) {
        
        String [] argg = arg.split("=");
        
        this.sale_id.add(Integer.valueOf(argg[0]));
        this.count_of_sales.add(Integer.valueOf(argg[1]));
	}
    
    public void removeSales (String arg) {

        String [] argg = arg.split("=");
        
        this.sale_id.remove(Integer.valueOf(argg[0]));
        this.count_of_sales.remove(Integer.valueOf(argg[1]));
	}
}
