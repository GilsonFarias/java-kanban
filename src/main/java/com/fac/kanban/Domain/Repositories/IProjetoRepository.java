package com.fac.kanban.Domain.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fac.kanban.Domain.Entities.Projeto;

public interface IProjetoRepository extends JpaRepository<Projeto, Long> {

}
