package br.edu.ipog.backend2.app.repository;

import br.edu.ipog.backend2.app.model.v2.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
