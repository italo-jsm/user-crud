package com.wa.test.watest.repository;

import com.wa.test.watest.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SystemUserRepository extends JpaRepository<Usuario, String> {
    public Optional<Usuario> findByUserUuid(String userUuid);
}
