package com.fac.kanban.Application.Services;

import org.springframework.stereotype.Service;

import com.fac.kanban.Application.DTOs.ProjetoDto;
import com.fac.kanban.Application.Exceptions.ApiResponse;
import com.fac.kanban.Domain.Entities.Projeto;

@Service
public interface IProjetoService {

    ApiResponse<ProjetoDto> criarProjeto(ProjetoDto projetoDto);

    ApiResponse<ProjetoDto> buscarProjetoPorId(Long id);

    ApiResponse<ProjetoDto> listarProjetos();

    ApiResponse<Projeto> atualizarProjeto(ProjetoDto projetoDto);

    ApiResponse<Void> deletarProjeto(Long id);

}
