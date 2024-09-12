package br.edu.ipog.backend2.app;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class ManyToManyTest {

    /**
     * Configuração inicial para rodar os testes.
     */
    @BeforeEach
    void setup(){
        log.warn("Método setup executado com sucesso.");
    }

    /**
     * Este método é executado depois de cada método.
     */
    @AfterEach
    void tearDown() {
        log.warn("Método tearDown executado com sucesso.");
    }

}
