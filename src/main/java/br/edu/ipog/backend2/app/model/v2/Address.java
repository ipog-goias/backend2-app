package br.edu.ipog.backend2.app.model.v2;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "address_v2")
@Table(name = "address")
public class Address{

    @Id
    @Column(name = "user_id")
    private Integer id;

    @Column(nullable = false, length = 45)
    private String street;

    @Column(nullable = false, length = 45)
    private String city;

    @OneToOne
    @JoinColumn(name = "user_id")
    @MapsId //Certifica se o ID seŕa o mesmo do User
    private User user;
}
