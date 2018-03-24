package com.borzfele.machinemother.repositories;

import com.borzfele.machinemother.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByName(String name);
    List<User> findAll();

}
