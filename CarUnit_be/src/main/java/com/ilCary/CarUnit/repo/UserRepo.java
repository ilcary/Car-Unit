package com.ilCary.CarUnit.repo;

import com.ilCary.CarUnit.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    @Query("select u from User u where upper(u.username) = upper(?1)")
    Optional<User> findByUsernameIgnoreCase(String username);


    User findByUsername(String n);
}
