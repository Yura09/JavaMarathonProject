package com.softserve.javamarathon.repository;

import com.softserve.javamarathon.entity.User;
import com.softserve.javamarathon.entity.enums.ROLE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findAllByRole(ROLE role);
    User findUserByEmail(String email);
}
