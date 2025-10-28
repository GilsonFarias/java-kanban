package com.fac.kanban.Application.Services;

import java.util.List;

import com.fac.kanban.Domain.Entities.Responsavel;

public interface IResponsavelService {
    
    void criarResponsavel(Responsavel responsavel);

    Responsavel buscarResponsavelPorId(Long id);
    
    List<Responsavel> listarResponsaveis();

    void atualizarResponsavel(Long id, Responsavel responsavel);

    void deletarResponsavel(Long id);  
}
