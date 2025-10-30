package com.fac.kanban.Application.Services;

import org.springframework.stereotype.Service;

import com.fac.kanban.Application.DTOs.ProjetoNovoDto;
import com.fac.kanban.Application.Exceptions.ApiResponse;
import com.fac.kanban.Domain.Entities.Projeto;

@Service
public interface IProjetoService {

    ApiResponse<Projeto> criarProjeto(ProjetoNovoDto projetoNovoDto);

    ApiResponse<Projeto> buscarProjetoPorId(Long id);

    ApiResponse<Projeto> listarProjetos();

    ApiResponse<Projeto> atualizarProjeto(ProjetoNovoDto projetoNovoDto);

    ApiResponse<Void> deletarProjeto(Long id);

}
