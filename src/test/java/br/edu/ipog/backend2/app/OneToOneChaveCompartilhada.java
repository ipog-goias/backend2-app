package br.edu.ipog.backend2.app;

import br.edu.ipog.backend2.app.model.v2.Address;
import br.edu.ipog.backend2.app.model.v2.User;
import br.edu.ipog.backend2.app.repository.UserRepository;
import br.edu.ipog.backend2.app.service.AddressService;
import br.edu.ipog.backend2.app.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
public class OneToOneChaveCompartilhada {

    //Declarar atributos que serão inseridos para podermos fazer o teste
    @Autowired
    UserService userService;

    @Autowired
    AddressService addressService;

    //Criar método @BeforeEach para inicializar o que for necessário para 'rodar' os testes e a aplicação não 'quebrar'.

    /**
     * Configuração inicial para rodar os testes.
     */
    @BeforeEach
    void setup() {

        log.warn("Método setup executado com sucesso.");
        userService.deleteAll();
    }

    @Test
    void testSaveUserAndAddress(){
        //Criamos o objeto usuário
        User user = new User();
        user.setUsername("Matuzalém");

        //Criamos o objeto address;
        Address address = new Address();
        address.setStreet("Rua das Orquídeas");
        address.setCity("Goiânia");

        //Associação entre usuário e endereço
        user.setAddress(address);
        address.setUser(user);

        addressService.create(address);

        //Salvar o User, e automaticamente, o Address (CascadeType.ALL)
        userService.create(user);
    }

    @Test
    void testFindById(){
        //Iniciar os objetos User e Anddress
        User user = new User();
        user.setUsername("testUser");

        //Salva o user primeiro para garantir que o ID seja gerado
        User savedUser = userService.create(user);

        Address address = new Address();
        address.setStreet("123 Main St");
        address.setCity("New York");
        address.setUser(user);

        //Salva User e Address
        addressService.create(address);

        //Buscar USER pelo ID
        User userFound = userService.read(user.getId());

        //Fazer as validações do nosso teste
        //Verifica se o User foi encontrado corretamente;
        assertNotNull(userFound);
        assertEquals("testUser",userFound.getUsername());


        //Verifica se o Address associada foi encontrado corretamente
        Address foundAddress = userFound.getAddress();

        //Fazer as validações asserts
        assertNotNull(foundAddress);
        assertEquals("123 Main St", foundAddress.getStreet());
        assertEquals("New York", foundAddress.getCity());

    }

    @Test
    void testUpdateUserAndAddress(){

    }


    @Test
    void testDeleteUserAndAddress(){

    }

}
