package com.marvin.easyfoodapi.infrastructure.repository;

import com.marvin.easyfoodapi.domain.model.Restaurante;
import com.marvin.easyfoodapi.domain.repository.RestauranteRepositoryQueries;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// ref:
@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

    @PersistenceContext
    private final EntityManager entityManager;

    public RestauranteRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Restaurante> findVersaoUm(String nome, BigDecimal
        taxaFreteInicial, BigDecimal taxaFreteFinal) {

        var jpqlQuery = new StringBuilder();
        jpqlQuery.append("from Restaurante ");
        jpqlQuery.append("where 0 = 0 ");

        var parametros = new HashMap<String, Object>();

        if (StringUtils.hasLength(nome)) {
            jpqlQuery.append("and lower(nome) like :nome ");
            parametros.put("nome", "%" + nome.toLowerCase() + "%");
        }

        if (taxaFreteInicial != null) {
            jpqlQuery.append("and taxaFrete >= :taxaInicial ");
            parametros.put("taxaInicial", taxaFreteInicial);
        }

        if (taxaFreteFinal != null) {
            jpqlQuery.append("and taxaFrete <= :taxaFinal ");
            parametros.put("taxaFinal", taxaFreteFinal);
        }

        TypedQuery<Restaurante> typedQuery = entityManager.createQuery(
            jpqlQuery.toString(), Restaurante.class);

//        parametros.forEach((chave, valor) -> typedQuery.setParameter(chave, valor));
        parametros.forEach(typedQuery::setParameter);

        return typedQuery.getResultList();

    }

    @Override
    public List<Restaurante> findVersaoDois(String nome, BigDecimal
        taxaFreteInicial, BigDecimal taxaFreteFinal) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Restaurante> criteriaQuery = criteriaBuilder.createQuery(Restaurante.class);

        Root<Restaurante> root = criteriaQuery.from(Restaurante.class);


        var predicates = new ArrayList<Predicate>();

        if (StringUtils.hasLength(nome)) {
            predicates.add(criteriaBuilder.like(root.get("nome"), "%" + nome.toLowerCase() + "%"));
        }

        if (taxaFreteInicial != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));
        }

        if (taxaFreteFinal != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));
        }


        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Restaurante> typedQuery = entityManager.createQuery(criteriaQuery);

        return typedQuery.getResultList();

    }






}
