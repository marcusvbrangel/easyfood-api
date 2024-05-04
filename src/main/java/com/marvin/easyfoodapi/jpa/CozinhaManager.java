package com.marvin.easyfoodapi.jpa;

import com.marvin.easyfoodapi.domain.model.Cozinha;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class CozinhaManager {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Cozinha> listar() {

        TypedQuery<Cozinha> query = entityManager.createQuery("from Cozinha", Cozinha.class);

        return query.getResultList();

    }

    @Transactional
    public Cozinha salvar(Cozinha cozinha) {

        return entityManager.merge(cozinha);

    }

    public Cozinha buscar(Long id) {

        return entityManager.find(Cozinha.class, id);

    }

    @Transactional
    public void excluir(Cozinha cozinha) {

        Cozinha cozinhaParaExcluir = buscar(cozinha.getId());

        entityManager.remove(cozinhaParaExcluir);

    }




}
