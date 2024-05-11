package com.marvin.easyfoodapi.domain.service;

import com.marvin.easyfoodapi.domain.model.Estado;
import com.marvin.easyfoodapi.domain.repository.EstadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {

    private final EstadoRepository estadoRepository;

    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    public Optional<Estado> buscar(Long id) {
        return estadoRepository.findById(id);
    }

    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    public void excluir(Long id) {
        estadoRepository.deleteById(id);
    }

}
