package br.edu.ipog.backend2.app.model.v1;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "address_v1")
@Table(name = "ADDRESS")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 45)
    private String street;

    @Column(length = 45)
    private String city;

    @OneToOne(mappedBy = "address")
    private User user;
}
