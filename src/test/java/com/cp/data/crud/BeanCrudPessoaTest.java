package com.cp.data.crud;

import com.cp.data.model.Pessoa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BeanCrudPessoaTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private EntityTransaction transaction;

    @InjectMocks
    private BeanCrudPessoa beanCrudPessoa;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(entityManager.getTransaction()).thenReturn(transaction);
    }

    @DisplayName("Test persist method")
    @Test
    void testPersist() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("John Doe");
        doNothing().when(entityManager).persist(pessoa);
        doNothing().when(transaction).begin();
        doNothing().when(transaction).commit();

        Exception exception = beanCrudPessoa.persist(pessoa);
        assertNull(exception, "Persist method should not throw an exception");
        verify(entityManager).persist(pessoa);
        verify(transaction).begin();
        verify(transaction).commit();
    }

    @DisplayName("Test find method")
    @Test
    void testFind() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Jane Doe");
        when(entityManager.find(Pessoa.class, pessoa.getId())).thenReturn(pessoa);

        Pessoa foundPessoa = beanCrudPessoa.find(pessoa.getId());
        assertNotNull(foundPessoa, "Find method should return a non-null object");
        assertEquals(pessoa.getNome(), foundPessoa.getNome(), "Found pessoa should have the same name");
        verify(entityManager).find(Pessoa.class, pessoa.getId());
    }

    @DisplayName("Test remove method")
    @Test
    void testRemove() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("John Smith");
        when(entityManager.merge(pessoa)).thenReturn(pessoa);
        doNothing().when(entityManager).remove(pessoa);
        doNothing().when(transaction).begin();
        doNothing().when(transaction).commit();

        Exception exception = beanCrudPessoa.remove(pessoa);
        assertNull(exception, "Remove method should not throw an exception");
        verify(entityManager).merge(pessoa);
        verify(entityManager).remove(pessoa);
        verify(transaction).begin();
        verify(transaction).commit();
    }

    @DisplayName("Test merge method")
    @Test
    void testMerge() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Initial Name");
        when(entityManager.merge(pessoa)).thenReturn(pessoa);
        doNothing().when(transaction).begin();
        doNothing().when(transaction).commit();

        Exception exception = beanCrudPessoa.merge(pessoa);
        assertNull(exception, "Merge method should not throw an exception");
        verify(entityManager).merge(pessoa);
        verify(transaction).begin();
        verify(transaction).commit();
    }
}