package com.bilitech.yilimusic.Repository;

import com.bilitech.yilimusic.enetity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface UserRepository extends JpaRepository<User, String>,
    JpaSpecificationExecutor<User> {

  Optional<User> findByUsername(String username);
}
