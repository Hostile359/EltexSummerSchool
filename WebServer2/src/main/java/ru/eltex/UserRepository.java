package ru.eltex;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findByFio(String fio);
    List<User> findByPhone(String phone);
}