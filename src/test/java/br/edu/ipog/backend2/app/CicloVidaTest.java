package br.edu.ipog.backend2.app;

import br.edu.ipog.backend2.app.model.Pessoa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

@Slf4j
@SpringBootTest
public class CicloVidaTest {

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

        manager.createQuery("delete from Pessoa").executeUpdate();

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
    void testarCicloTransientParaManaged(){

        log.info("Iniciado teste - testarCicloTransientParaManaged");
//        Pessoa pessoa = new Pessoa();
//        pessoa.setNome();
//        pessoa.setEmail();
//        pessoa.setDataCadastro();
        Pessoa pessoa = Pessoa.builder()
                .nome("Aluno 1").email("aluno1@ipog.edu.br").dataCadastro(LocalDateTime.now()).build();

        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        //a mágica de gravar no banco de dados acontece aqui.
        manager.persist(pessoa);
        //aqui estou gravando o registro no banco de dados.
        // O objeto que estava em transiente tornar-se-á managed (gerenciado).
        transaction.commit();
        log.info("Fim teste - testarCicloTransientParaManaged");
    }

    @Test
    void testarCicloManagedParaRemoved(){
        log.info("Iniciado teste - testarCicloManagedParaRemoved");
        //Criar o objeto e preencher as informações
        Pessoa pessoa = Pessoa.builder()
                .nome("Aluno 1 - testarCicloManagedParaRemoved")
                .email("aluno1@ipog.edu.br")
                .dataCadastro(LocalDateTime.now()).build();

        //Inicio uma transação
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        manager.persist(pessoa);//gravar --> sql INSERT
        //transaction.commit();
        manager.remove(pessoa);//remover --> sql DELETE

        //Finalizo a transação
        transaction.commit();

        assertFalse(manager.contains(pessoa), "esta unidade não deveria pertencer ao Contexto de Persistência.");
        assertNull(manager.find(Pessoa.class, pessoa.getId()),
                String.format("Não deveria existir registro para este id: %d", pessoa.getId()));

        log.info("Fim teste - testarCicloManagedParaRemoved");
    }

    @Test
    void testarCicloDetachedParaManaged(){
        log.info("Iniciado teste - testarCicloDetachedParaManaged");
        //Criar o objeto e preencher as informações

        //Inicio uma transação
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();


        //Finalizo a transação
        transaction.commit();

        log.info("Fim teste - testarCicloDetachedParaManaged");
    }

    @Test
    void testarCicloManagedParaDetached(){
        log.info("Iniciado teste - testarCicloManagedParaDetached");

        //Criar o objeto e preencher as informações

        //Inicio uma transação
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();


        //Finalizo a transação
        transaction.commit();
        log.info("Fim teste - testarCicloManagedParaDetached");
    }

}
