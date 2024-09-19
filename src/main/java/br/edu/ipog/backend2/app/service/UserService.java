package br.edu.ipog.backend2.app.service;

import br.edu.ipog.backend2.app.interfaces.GenericOperations;
import br.edu.ipog.backend2.app.model.v2.User;
import br.edu.ipog.backend2.app.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements GenericOperations<User, Integer> {

    @Autowired
    UserRepository userRepository;

    @Override
    //se alterar banco, adicionar anotação @Transactional
    @Transactional(rollbackOn = Exception.class)
    public User create(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public User read(Integer id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    //se alterar banco, adicionar anotação @Transactional
    @Transactional(rollbackOn = Exception.class)
    public User update(Integer id, User entity) {
        User userfound = this.read(id);
        if(userfound != null) {
            entity.setId(userfound.getId());
        }

        return userRepository.save(entity);
    }

    @Override
    //se alterar banco, adicionar anotação @Transactional
    @Transactional(rollbackOn = Exception.class)
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    //se alterar banco, adicionar anotação @Transactional
    @Transactional(rollbackOn = Exception.class)
    public void deleteAll(){
        userRepository.deleteAll();
    }
}
