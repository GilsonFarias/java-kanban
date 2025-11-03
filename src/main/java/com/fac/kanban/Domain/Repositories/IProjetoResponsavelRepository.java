package com.fac.kanban.Domain.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fac.kanban.Domain.Entities.Projeto;
import com.fac.kanban.Domain.Entities.ProjetoResponsavel;
import com.fac.kanban.Domain.Entities.Responsavel;

public interface IProjetoResponsavelRepository extends JpaRepository<ProjetoResponsavel, Long> {

    List<ProjetoResponsavel> findByProjeto(Projeto projeto);
    
    List<ProjetoResponsavel> findByProjeto_Id(Long projetoId);

    List<ProjetoResponsavel> findByResponsavel(Responsavel responsavel);

    List<ProjetoResponsavel> findByResponsavel_Id(Long responsavelId);

    ProjetoResponsavel findByProjeto_IdAndResponsavel_Id(Long projetoId, Long responsavelId);

    void deleteByProjeto_Id(Long projetoId);

}
