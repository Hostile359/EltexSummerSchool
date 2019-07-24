package ru.eltex;

import lombok.*;

@Setter @Getter
abstract class User implements CSV{
    protected Integer id;
    protected String fio;
    protected String phone;
    protected String email;
    
    /*public Integer getId () {

		return this.id;
	}

	public String getFio () {

		return this.fio;
	}

	public String getPhone () {

		return this.phone;
	}

	public String getEmail () {

		return this.email;
	}
		
	public void setId (String arg) {

		this.id = Integer.valueOf(arg);
	}
	

	public void setFio (String arg) {

		this.fio = arg;
	}

	public void setPhone (String arg) {

		this.phone = arg;
	}

	public void setEmail (String arg) {

		this.email = arg;
	}*/

    //public String toCSV();
    //public void fromCSV(String str);
}
