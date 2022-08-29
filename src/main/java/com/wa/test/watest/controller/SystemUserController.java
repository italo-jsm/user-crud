package com.wa.test.watest.controller;

import com.wa.test.watest.domain.Usuario;
import com.wa.test.watest.exceptions.UserUpdateException;
import com.wa.test.watest.service.SystemUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class SystemUserController {
    private SystemUserService systemUserService;

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody Usuario systemUser){
        var userUid = systemUserService.createNewUser(systemUser).getUserUuid();
        return ResponseEntity.created(URI.create("http://localhost:8080/users/" + userUid)).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id){
        return systemUserService.findUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody Usuario systemUser){
        try{
            return ResponseEntity.ok(systemUserService.updateUser(systemUser));
        }catch (UserUpdateException exception){
            return ResponseEntity.badRequest().body(exception.getMsg());
        }
    }
}
