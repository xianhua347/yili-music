package com.bilitech.yilimusic.Repository;

import com.bilitech.yilimusic.enetity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {
    private UserRepository userRepository;

    @Autowired
    public UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    void findById() {
        for (User user : userRepository.findAll()) {
            System.out.println(user.getNickname());
        }
    }
}