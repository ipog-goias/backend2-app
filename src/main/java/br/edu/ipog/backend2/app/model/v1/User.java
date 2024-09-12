package br.edu.ipog.backend2.app.model.v1;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "[USER]")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 45)
    private String username;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_idXXXXXX", referencedColumnName = "id")
    private Address address;

}
