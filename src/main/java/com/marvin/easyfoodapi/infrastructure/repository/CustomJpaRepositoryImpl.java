package com.marvin.easyfoodapi.infrastructure.repository;

import com.marvin.easyfoodapi.domain.repository.CustomJpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.util.Optional;

// ref:
public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID>
    implements CustomJpaRepository<T, ID> {

    private final EntityManager entityManager;

    public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Optional<T> buscarPrimeiro() {

        var jpqlQuery = "from " + getDomainClass().getName();

        T entity = entityManager.createQuery(jpqlQuery, getDomainClass())
            .setMaxResults(1)
            .getSingleResult();

        return Optional.ofNullable(entity);

    }
}
