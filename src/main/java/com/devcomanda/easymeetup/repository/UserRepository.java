package com.devcomanda.easymeetup.repository;

import com.devcomanda.easymeetup.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String lowercaseEmail);
}
