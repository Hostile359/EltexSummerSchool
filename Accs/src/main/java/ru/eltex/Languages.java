package ru.eltex;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Languages {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private Integer id;
    @Column(unique = true)
    @Getter @Setter private String name;

    @Transient
    @Getter @Setter private static Integer counter_id = 0;

    Languages(String name){
        this.name = name;
    }
}
