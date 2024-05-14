package com.marvin.easyfoodapi.domain.repository;

import com.marvin.easyfoodapi.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long>,
    RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante> {

    List<Restaurante> queryByTaxaFreteBetween(BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

    @Query("from Restaurante where lower(nome) like %:nome% and cozinha.id = :id")
    List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinha);

    // Note: lendo query de um arquivo xml da pasta resources/META-INF/orm.xml ...
    List<Restaurante> consultarRestaurantePorNome(String nome, @Param("id") Long cozinha);

    List<Restaurante> findByNomeContainingIgnoreCaseAndCozinhaId(String nome, Long cozinha);

    Optional<Restaurante> findFirstByNomeContaining(String nome);

    List<Restaurante> findTop2ByNomeContainingOrderByNomeAsc(String nome);

    int countByCozinhaId(Long cozinha);

}
