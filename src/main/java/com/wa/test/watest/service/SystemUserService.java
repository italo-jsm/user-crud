package com.wa.test.watest.service;

import com.wa.test.watest.domain.Usuario;
import com.wa.test.watest.exceptions.UserUpdateException;
import com.wa.test.watest.repository.SystemUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class SystemUserService {

    private SystemUserRepository systemUserRepository;

    public Usuario createNewUser(Usuario systemUser){
        systemUser.setUserUuid(UUID.randomUUID().toString());
        systemUser.setCreatedAt(LocalDate.now());
        return systemUserRepository.save(systemUser);
    }

    public Optional<Usuario> findUserById(String id) {
        return systemUserRepository.findByUserUuid(id);
    }

    public Usuario updateUser(Usuario user){
        var existingUser = systemUserRepository.findByUserUuid(user.getUserUuid());
        if(existingUser.isEmpty()){
            throw new UserUpdateException("Usuario nao encontrado");
        }
        var userToUpdate = existingUser.get();
        userToUpdate.setNome(user.getNome());
        userToUpdate.setDocument(user.getDocument());
        userToUpdate.setUpdatedAt(LocalDate.now());
        return systemUserRepository.save(userToUpdate);
    }
}
