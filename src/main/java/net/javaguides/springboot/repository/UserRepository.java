package net.javaguides.springboot.repository;

import net.javaguides.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// extend JpaRepository and pass parameters (first the entity, second the primary key Long

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

}
