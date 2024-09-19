package br.edu.ipog.backend2.app;

import br.edu.ipog.backend2.app.model.v1.Address;
import br.edu.ipog.backend2.app.model.v1.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest
public class OneToOneBasicoTest {

    //Declarar atributos que serão inseridos para podermos fazer o teste
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
    private EntityManager manager;

    //Criar método @BeforeEach para inicializar o que for necessário para 'rodar' os testes e a aplicação não 'quebrar'.

    /**
     * Configuração inicial para rodar os testes.
     */
    @BeforeEach
    void setup(){
        log.warn("Método setup executado com sucesso.");
        //Antes de rodar cada método, iremos deletar os registros que foram inseridos na tabela pessoa.
        manager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        //Tudo que ocorrer dentro de bloco begin/commit é
        // garantia que o dado será persistido(insert ou update) ou removido (deletado)

        manager.createQuery("delete from User").executeUpdate();

        transaction.commit();
    }

    /**
     * Este método é executado depois de cada método.
     */
    @AfterEach
    void tearDown(){
        log.warn("Método tearDown executado com sucesso.");
        if(manager.isOpen()){
            manager.close();
        } //após o término de cada test o contexto de persistência é fechado.
    }

    @Test
    public void testUserWithAddress(){

        //Criar o objeto Address
        Address address = new Address();
        address.setStreet("Rua do Além");
        address.setCity("Goiânia");


        //Criar o objeto User;
        User user = new User();
        user.setUsername("usuario_exemplo");
        user.setAddress(address);

        //Persistência dos dados
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        manager.persist(user);

        transaction.commit();

        //Buscar o usuário no banco de dados
        User userConsultado = manager.find(User.class, user.getId());

        //Verificar se o User e o Address formam persistidos corretamente.
        assertNotNull(userConsultado);

        assertNotNull(userConsultado.getAddress());

        assertEquals("usuario_exemplo", userConsultado.getUsername());
        assertEquals("Rua do Além", userConsultado.getAddress().getStreet());
        assertEquals("Goiânia", userConsultado.getAddress().getCity());

        log.info("Fim teste - testUserWithAddress");
    }

}
