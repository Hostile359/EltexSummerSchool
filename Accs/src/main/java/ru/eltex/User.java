package ru.eltex;

import lombok.*;

@Setter @Getter
abstract class User implements CSV{
    protected Integer id;
    protected String fio;
    protected String phone;
    protected String email;
    

}
