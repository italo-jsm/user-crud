package com.wa.test.watest.domain.dto;

import com.wa.test.watest.domain.Usuario;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioDto {
    private String nome;
    private String document;

    public static UsuarioDto toDto(Usuario usuario){
        var usuarioDto = new UsuarioDto();
        usuarioDto.setDocument(usuarioDto.getDocument());
        usuarioDto.setNome(usuarioDto.getNome());
        return usuarioDto;
    }

    public Usuario toUsuario(){
        var usuario = new Usuario();
        usuario.setDocument(this.getDocument());
        usuario.setNome(this.getNome());
        return usuario;
    }
}


