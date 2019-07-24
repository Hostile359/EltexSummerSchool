package ru.eltex;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Languages {
    @Id
    @GeneratedValue
    @Getter @Setter private Integer id;
    @Getter @Setter private String name;
    Languages() {};
    Languages(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
