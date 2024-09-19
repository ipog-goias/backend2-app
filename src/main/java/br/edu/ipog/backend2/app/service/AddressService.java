package br.edu.ipog.backend2.app.service;

import br.edu.ipog.backend2.app.interfaces.GenericOperations;
import br.edu.ipog.backend2.app.model.v2.Address;
import br.edu.ipog.backend2.app.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService implements GenericOperations<Address, Integer> {

    @Autowired
    AddressRepository addressRepository;

    @Override
    public Address create(Address entity) {
        return addressRepository.save(entity);
    }

    @Override
    public Address read(Integer id) {
        return addressRepository.findById(id).orElseThrow();
    }

    @Override
    public Address update(Integer id, Address entity) {
        return addressRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
        addressRepository.deleteById(id);
    }
}
