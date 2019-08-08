package ru.eltex;

import lombok.Getter;
import lombok.Setter;

class Task <T extends User, V extends User > {

    @Getter @Setter private T owner;
    @Getter @Setter private V qa;
    @Getter @Setter private String title;
    @Getter @Setter private String description;

}