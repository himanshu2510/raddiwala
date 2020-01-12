package com.raddiwala.web.repository;

import com.raddiwala.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByUsername(String username);

}
