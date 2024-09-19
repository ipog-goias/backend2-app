package br.edu.ipog.backend2.app.model.v2;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "user_v2")
@Table(name = "user_v2")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 45)
    private String username;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Address address;

}
