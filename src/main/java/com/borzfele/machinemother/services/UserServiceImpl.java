package com.borzfele.machinemother.services;

import com.borzfele.machinemother.model.User;
import com.borzfele.machinemother.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByName(String name) {
        System.out.println("findbyname, and name: " + name);
        return userRepository.findByName(name);
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        System.out.println("loadbyname, name: " + name);

        User user = findByName(name);

        if (user == null) {
            System.out.println("user doesn't exist");
            throw new UsernameNotFoundException(name);
        }

        System.out.println(user);

        return new UserDetailsImpl(user);
    }
}
