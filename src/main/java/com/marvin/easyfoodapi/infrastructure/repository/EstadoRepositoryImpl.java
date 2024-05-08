package com.marvin.easyfoodapi.infrastructure.repository;

import com.marvin.easyfoodapi.domain.model.Estado;
import com.marvin.easyfoodapi.domain.repository.EstadoRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Estado> listar() {

        TypedQuery<Estado> query = entityManager.createQuery("from Estado", Estado.class);

        return query.getResultList();

    }

    @Transactional
    public Estado salvar(Estado estado) {

        return entityManager.merge(estado);

    }

    public Estado buscar(Long id) {

        return entityManager.find(Estado.class, id);

    }

    @Transactional
    public void excluir(Estado estado) {

        Estado estadoParaExcluir = buscar(estado.getId());

        entityManager.remove(estadoParaExcluir);

    }

}
