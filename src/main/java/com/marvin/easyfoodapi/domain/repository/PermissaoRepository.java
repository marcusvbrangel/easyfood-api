package com.marvin.easyfoodapi.domain.repository;

import com.marvin.easyfoodapi.domain.model.FormaPagamento;
import com.marvin.easyfoodapi.domain.model.Permissao;

import java.util.List;

public interface PermissaoRepository {

    List<Permissao> listar();

    Permissao buscar(Long id);

    Permissao salvar(Permissao permissao);

    void excluir(Permissao permissao);

}
