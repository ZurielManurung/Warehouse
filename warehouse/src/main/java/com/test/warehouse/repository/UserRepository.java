package com.test.warehouse.repository;

import com.test.warehouse.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByDeleted(Boolean deleted);

    Optional<User> findByUserName(String userName);
}
