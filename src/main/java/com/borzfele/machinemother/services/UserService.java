package com.borzfele.machinemother.services;

import com.borzfele.machinemother.model.User;

public interface UserService {

    User findByName(String name);

}
