package com.fac.kanban.Domain.Entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "projeto_responsavel")
public class ProjetoResponsavel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projeto_id", nullable = false)
    private Projeto projeto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsavel_id", nullable = false)
    private Responsavel responsavel;

    @Temporal(TemporalType.DATE)
    @Column(name = "dt_inicio")
    private Date dt_inicio;

    @Temporal(TemporalType.DATE)
    @Column(name = "dt_fim")
    private Date dt_fim;

    public ProjetoResponsavel(Long id, Projeto projeto, Responsavel responsavel, Date dt_inicio, Date dt_fim) {
        this.id = id;
        this.projeto = projeto;
        this.responsavel = responsavel;
        this.dt_inicio = dt_inicio;
        this.dt_fim = dt_fim;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Projeto getProjeto() {
        return projeto;
    }
    public void setProjetoId(Projeto projeto) {
        this.projeto = projeto;
    }
    public Responsavel getResponsavel() {
        return responsavel;
    }
    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }
    public Date getDt_inicio() {
        return dt_inicio;
    }
    public void setDt_inicio(Date dt_inicio) {
        this.dt_inicio = dt_inicio;
    }
    public Date getDt_fim() {
        return dt_fim;
    }
    public void setDt_fim(Date dt_fim) {
        this.dt_fim = dt_fim;
    }

}
