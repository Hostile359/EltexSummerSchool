package ru.eltex;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Sales{
	@Id
	@GeneratedValue
	@Setter @Getter private Integer id;
	@Setter @Getter private String name;
	@Setter @Getter private Integer price;
    
    public void printInf() {
        
        System.out.println("Sale id: " + this.id);
        System.out.println("Sale name: " + this.name);
        System.out.println("Sale price: " + this.price);
        System.out.println();
    }

	public String toCSV() {

		return this.id.toString() + ";" + this.name + ";" + this.price + ";";
	}

	public Integer fromCSV(String str) {

		String [] arg = str.split(";");
        if(arg.length == 3) {
            setId (Integer.valueOf(arg [0]));
            setName (arg [1]);
            setPrice (Integer.valueOf(arg [2]));
        }else
            return -1;
            
        return 0;
    }
}
