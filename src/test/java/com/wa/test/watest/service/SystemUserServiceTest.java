package com.wa.test.watest.service;

import com.wa.test.watest.domain.Usuario;
import com.wa.test.watest.exceptions.UserUpdateException;
import com.wa.test.watest.repository.SystemUserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
public class SystemUserServiceTest {
    @Mock
    private SystemUserRepository systemUserRepository;

    @InjectMocks
    private SystemUserService systemUserService;

    @Test
    public void shouldSaveUser(){
        Usuario usuario = new Usuario();
        usuario.setDocument("22233344455");
        usuario.setNome("Nome");
        Mockito.when(systemUserRepository.save(any())).thenReturn(usuario);
        var u = systemUserService.createNewUser(usuario);
        Assert.assertNotNull(u.getUserUuid());
        Assert.assertFalse(u.getUserUuid().isEmpty());
    }

    @Test
    public void shouldThrowExceptionOnUpdateNotExistingUser(){
        Usuario usuario = new Usuario();
        usuario.setUserUuid("uuid");
        usuario.setDocument("22233344455");
        usuario.setNome("Nome");
        Mockito.when(systemUserRepository.findByUserUuid(any())).thenReturn(Optional.empty());
        try{
            systemUserService.updateUser(usuario);
            Assert.fail("Should throw exception");
        }catch (Exception e){
            Assert.assertTrue(e instanceof UserUpdateException);
        }
    }

    @Test
    public void shouldFindOneUser(){
        Usuario usuario = new Usuario();
        usuario.setUserUuid("uuid");
        usuario.setDocument("22233344455");
        usuario.setNome("Nome");
        Mockito.when(systemUserRepository.findByUserUuid(any())).thenReturn(Optional.of(usuario));
        var foundUser = systemUserService.findUserById("uuid");
        Assert.assertTrue(foundUser.isPresent());
        Assert.assertEquals("22233344455", foundUser.get().getDocument());
        Assert.assertEquals("Nome", foundUser.get().getNome());
    }

    @Test
    public void shouldUpdateOneUser(){
        Usuario usuario = new Usuario();
        usuario.setUserUuid("uuid");
        usuario.setDocument("22233344455");
        usuario.setNome("Nome");
        Mockito.when(systemUserRepository.findByUserUuid(any())).thenReturn(Optional.of(usuario));
        Mockito.when(systemUserRepository.save(any())).thenReturn(usuario);
        Usuario userToUpdate = new Usuario();
        userToUpdate.setUserUuid("uuid");
        userToUpdate.setDocument("33344455566");
        userToUpdate.setNome("Nome Atualizado");
        var updatedUser = systemUserService.updateUser(userToUpdate);
        Assert.assertEquals("33344455566", updatedUser.getDocument());
        Assert.assertEquals("Nome Atualizado", updatedUser.getNome());
        Assert.assertNotNull(updatedUser.getUpdatedAt());
    }
}
