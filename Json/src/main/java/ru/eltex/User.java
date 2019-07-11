package ru.eltex;

import org.codehaus.jackson.map.*;
import java.io.*;

class User implements JSON{
    protected Integer id;
    protected String fio;
    protected String phone;
    protected String email;
    
    User() {}
    
    User(Integer id, String fio, String phone, String email) {
        this.id = id;
        this.fio = fio;
        this.phone = phone;
        this.email = email;
    }
    
    public void printInf() {
        
        System.out.println("User id: " + this.id);
        System.out.println("User fio: " + this.fio);
        System.out.println("User phone: " + this.phone);
        System.out.println("User email: " + this.email);
		
        System.out.println();
        System.out.println();
    }
    
    public Integer getId () {

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
	}

    public String toJSON() {
        String userJSON;
        try {
            User u1 = new User(this.id, this.fio, this.phone, this.email);
            ObjectMapper mapper = new ObjectMapper();
            userJSON = mapper.writeValueAsString(u1);
        }
        catch (IOException error) {
            System.err.print(error.getMessage()); 
            return null;
        }
        
        return userJSON;
    }
    
    public Integer fromJSON(String str) {
        User u1 = new User();
        try {
            ObjectMapper mapper = new ObjectMapper();
            
            u1 = mapper.readValue(str, User.class);
            
            this.id = u1.getId();
            this.fio = u1.getFio();
            this.phone = u1.getPhone();
            this.email = u1.getEmail();
        }
        catch (IOException error) {
            System.err.print(error.getMessage()); 
            return -1;
        }
        
        return 0;
    }
}
