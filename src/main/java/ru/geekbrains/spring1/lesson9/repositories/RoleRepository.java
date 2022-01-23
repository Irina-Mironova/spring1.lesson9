package ru.geekbrains.spring1.lesson9.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.spring1.lesson9.entities.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
