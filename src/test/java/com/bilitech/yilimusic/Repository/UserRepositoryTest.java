package com.bilitech.yilimusic.Repository;

import com.bilitech.yilimusic.Enums.Gender;
import com.bilitech.yilimusic.enetity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserRepositoryTest {
    private UserRepository userRepository;

    @Autowired
    public UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    void findById() {
        final User user1 = new User();
        user1.setPassword("11");
        user1.setEnabled(false);
        user1.setLocked(true);
        user1.setGender(Gender.MALE);
        userRepository.save(user1);
    }
}