package com.taher.book.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface tokenRepository extends JpaRepository<token,Integer>
{
    Optional<token> findBytoken(String token);
}
