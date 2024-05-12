package com.marvin.easyfoodapi.domain.repository;

import com.marvin.easyfoodapi.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

    List<Cozinha> findTodasByNome(String nome);

    Optional<Cozinha> findByNome(String nome);

    Optional<Cozinha> findByNomeOrderByNome(String nome);

    List<Cozinha> findTodasByNomeContaining(String nome);

    boolean existsByNome(String nome);

}
