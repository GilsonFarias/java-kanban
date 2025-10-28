package com.fac.kanban.Domain.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fac.kanban.Domain.Entities.Responsavel;

public interface IResponsavelRepository extends JpaRepository<Responsavel, Long> {

}
