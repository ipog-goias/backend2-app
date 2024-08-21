package br.edu.ipog.backend2.app.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "PESSOA")
public class Pessoa {

    @Id //indica chave primária
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String nome;

    private String email;

    private LocalDateTime dataCadastro;
}
