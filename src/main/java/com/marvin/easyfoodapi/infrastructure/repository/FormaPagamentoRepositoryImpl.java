package com.marvin.easyfoodapi.infrastructure.repository;

import com.marvin.easyfoodapi.domain.model.Cozinha;
import com.marvin.easyfoodapi.domain.model.FormaPagamento;
import com.marvin.easyfoodapi.domain.repository.FormaPagamentoRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository {


    @PersistenceContext
    private EntityManager entityManager;

    public List<FormaPagamento> listar() {

        TypedQuery<FormaPagamento> query = entityManager.createQuery("from FormaPagamento", FormaPagamento.class);

        return query.getResultList();

    }

    @Transactional
    public FormaPagamento salvar(FormaPagamento formaPagamento) {

        return entityManager.merge(formaPagamento);

    }

    public FormaPagamento buscar(Long id) {

        return entityManager.find(FormaPagamento.class, id);

    }

    @Transactional
    public void excluir(FormaPagamento formaPagamento) {

        FormaPagamento formaPagamentoParaExcluir = buscar(formaPagamento.getId());

        entityManager.remove(formaPagamentoParaExcluir);

    }

}
