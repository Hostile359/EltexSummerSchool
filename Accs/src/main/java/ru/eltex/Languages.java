package ru.eltex;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Languages {
    @Id
    @Getter @Setter private Integer id;
    @Column(unique = true) @NaturalId
    @Getter @Setter private String name;

    @Transient
    @Getter @Setter private static ArrayList<Languages> lang_list = new ArrayList<Languages>();

    Languages(String name){
        this.name = name;
    }

    public static Languages getLang(String lang){

        for(int i = 0; i < lang_list.size(); i++){
            if(lang_list.get(i).name.equals(lang)){
                return lang_list.get(i);
            }
        }
        Languages temp = new Languages(lang_list.size(), lang);
        lang_list.add(temp);
        return temp;
    }

}
