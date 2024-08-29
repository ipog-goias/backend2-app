package br.edu.ipog.backend2.app.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Data
@Entity
@Table(name = "PESSOAZ")
public class Pessoa {

    @Id //indica chave primária
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String nome;

    private String email;

    private LocalDateTime dataCadastro;
}
