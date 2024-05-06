package com.marvin.easyfoodapi.jpa;

import com.marvin.easyfoodapi.domain.model.Cozinha;
import com.marvin.easyfoodapi.domain.model.Restaurante;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class RestauranteManager {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Restaurante> listar() {

        TypedQuery<Restaurante> query = entityManager.createQuery("from Restaurante", Restaurante.class);

        return query.getResultList();

    }

    @Transactional
    public Restaurante salvar(Restaurante restaurante) {

        return entityManager.merge(restaurante);

    }

    public Restaurante buscar(Long id) {

        return entityManager.find(Restaurante.class, id);

    }

    @Transactional
    public void excluir(Restaurante restaurante) {

        Restaurante restauranteParaExcluir = buscar(restaurante.getId());

        entityManager.remove(restauranteParaExcluir);

    }

}
