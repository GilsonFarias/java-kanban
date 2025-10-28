package com.fac.kanban.Application.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fac.kanban.Domain.Entities.Projeto;

@Service
public interface IProjetoService {

    void criarProjeto(Projeto projeto);

    Projeto buscarProjetoPorId(Long id);

    List<Projeto> listarProjetos();

    void atualizarProjeto(Long id, Projeto projeto);

    void deletarProjeto(Long id);

}
