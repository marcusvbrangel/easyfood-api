package com.marvin.easyfoodapi.infrastructure.repository;

import com.marvin.easyfoodapi.domain.model.Cozinha;
import com.marvin.easyfoodapi.domain.model.Permissao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

public class PermissaoRepositoryImpl {


    @PersistenceContext
    private EntityManager entityManager;

    public List<Permissao> listar() {

        TypedQuery<Permissao> query = entityManager.createQuery("from Permissao", Permissao.class);

        return query.getResultList();

    }

    @Transactional
    public Permissao salvar(Permissao permissao) {

        return entityManager.merge(permissao);

    }

    public Permissao buscar(Long id) {

        return entityManager.find(Permissao.class, id);

    }

    @Transactional
    public void excluir(Permissao permissao) {

        Permissao permissaoParaExcluir = buscar(permissao.getId());

        entityManager.remove(permissaoParaExcluir);

    }

}
