package ru.eltex;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Document
public class Calls {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter protected Integer id;
    @Getter @Setter protected Integer id_1;
    @Getter @Setter protected Integer id_2;
    @Getter @Setter protected String time;
    @Getter @Setter protected Date date;

    Calls() {}

    Calls (Integer id, Integer id_1, Integer id_2, String time, Date date) {
        this.id = id;
        this.id_1 = id_1;
        this.id_2 = id_2;
        this.time = time;
        this.date = date;
    }

    public void printInf() {

        System.out.println("Call id: " + this.id);
        System.out.println("First user id: " + this.id_1);
        System.out.println("Second user id: " + this.id_2);
        System.out.println("Call's duration: " + this.time);
        System.out.println("Call's date: " + this.date);

        System.out.println();
        System.out.println();
    }

    public String toCSV() {

        return Integer.toString(this.id) + ";" + this.id_1 + ";" + this.id_2 + ";" + this.time + ";" + this.date + ";";
    }

    public Integer fromCSV(String str) throws ParseException {
        String [] arg = str.split(";");
        if(arg.length == 5) {
            setId (Integer.valueOf(arg [0]));
            setId_1(Integer.valueOf(arg [1]));
            setId_2(Integer.valueOf(arg [2]));
            setTime(arg[3]);
            setDate(new SimpleDateFormat("dd/MM/yyyy").parse(arg[4]));
        }else
            return -1;

        return 0;
    }
}
