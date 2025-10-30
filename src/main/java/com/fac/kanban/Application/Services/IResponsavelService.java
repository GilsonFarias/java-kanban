package com.fac.kanban.Application.Services;

import com.fac.kanban.Application.DTOs.ResponsavelDto;
import com.fac.kanban.Application.Exceptions.ApiResponse;
import com.fac.kanban.Domain.Entities.Responsavel;

public interface IResponsavelService {

    ApiResponse<Responsavel> criarResponsavel(ResponsavelDto responsavelDto);

    ApiResponse<Responsavel> buscarResponsavelPorId(Long id);
    
    ApiResponse<Responsavel> listarResponsaveis();

    ApiResponse<Responsavel> atualizarResponsavel(ResponsavelDto responsavelDto);

    ApiResponse<Void> deletarResponsavel(Long id);
}
