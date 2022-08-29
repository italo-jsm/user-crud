package com.wa.test.watest.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
public class Usuario {
    @Id
    private String userUuid;
    private String nome;
    private String document;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
