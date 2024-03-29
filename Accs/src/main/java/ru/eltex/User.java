package ru.eltex;

import lombok.*;

import javax.persistence.*;

@MappedSuperclass
abstract class User implements CSV, Comparable<User> {
    @Id
    @Setter @Getter protected Integer id;
    @Setter @Getter protected String fio;
    @Setter @Getter protected String phone;
    @Setter @Getter protected String email;

    public int compareTo (User other) {

        return this.compareTo(other);
    }
}
