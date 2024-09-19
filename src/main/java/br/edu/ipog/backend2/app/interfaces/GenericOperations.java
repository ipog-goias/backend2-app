package br.edu.ipog.backend2.app.interfaces;

/**
 * @see https://docs.oracle.com/javase/tutorial/java/generics/types.html
 */
public interface GenericOperations<T, N> {


    T create(T entity); //cadastra um registro

    T read(N id); //fazer a consulta baseada em número

    T update(N id, T entity); //passo a chave para consulta e depois a entidade para persistência

    void delete(N id);
}
