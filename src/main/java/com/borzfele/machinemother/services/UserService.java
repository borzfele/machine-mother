package com.borzfele.machinemother.services;

import com.borzfele.machinemother.models.User;

public interface UserService {

    User findByName(String name);

}
