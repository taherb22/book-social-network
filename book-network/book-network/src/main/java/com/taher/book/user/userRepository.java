package com.taher.book.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface userRepository extends JpaRepository<user, Integer> {

    Optional<user> findByemail(String email);
}
