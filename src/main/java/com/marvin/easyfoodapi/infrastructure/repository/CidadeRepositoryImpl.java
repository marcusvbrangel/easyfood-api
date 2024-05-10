package com.marvin.easyfoodapi.infrastructure.repository;

import com.marvin.easyfoodapi.domain.model.Cidade;
import com.marvin.easyfoodapi.domain.repository.CidadeRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Cidade> listar() {

        TypedQuery<Cidade> query = entityManager.createQuery("from Cidade", Cidade.class);

        return query.getResultList();

    }

    @Transactional
    public Cidade salvar(Cidade cidade) {

        return entityManager.merge(cidade);

    }

    public Cidade buscar(Long id) {

        return entityManager.find(Cidade.class, id);

    }

    @Transactional
    public void excluir(Cidade cidade) {

        Cidade cidadeParaExcluir = buscar(cidade.getId());

        entityManager.remove(cidadeParaExcluir);

    }

}
