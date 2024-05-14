package com.marvin.easyfoodapi.infrastructure.repository;

import com.marvin.easyfoodapi.domain.model.Restaurante;
import com.marvin.easyfoodapi.domain.repository.RestauranteRepositoryQueries;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
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
    public List<Restaurante> findXyz(String nome, BigDecimal
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

}
