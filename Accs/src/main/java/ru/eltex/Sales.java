package ru.eltex;

public class Sales{
    private Integer id;
    private String name;
    private Integer price;
    
    public void printInf() {
        
        System.out.println("Sale id: " + this.id);
        System.out.println("Sale name: " + this.name);
        System.out.println("Sale price: " + this.price);
        System.out.println();
    }
    
    public Integer getId () {

		return this.id;
	}

	public String getName () {

		return this.name;
	}

	public Integer getPrice () {

		return this.price;
	}
    
    public void setId (String arg) {

		this.id = Integer.valueOf(arg);
	}
	

	public void setName (String arg) {

		this.name = arg;
	}

	public void setPrice (String arg) {

		this.price = Integer.valueOf(arg);
	}

	public String toCSV() {

		return this.id.toString() + ";" + this.name + ";" + this.price + ";";
	}

	public Integer fromCSV(String str) {

		String [] arg = str.split(";");
        if(arg.length == 3) {
            setId (arg [0]);
            setName (arg [1]);
            setPrice (arg [2]);
        }else
            return -1;
            
        return 0;
    }
}
