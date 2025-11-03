package com.fac.kanban.Domain.Entities;

import java.util.Date;

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
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "projeto_responsavel", uniqueConstraints = { @UniqueConstraint(columnNames = { "projeto_id", "responsavel_id" })})
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
    @Column(name = "dt_registro", nullable = false, columnDefinition = "DATE DEFAULT (CURRENT_DATE)")
    private Date dtRegistro;

    public ProjetoResponsavel() {
    }

    public ProjetoResponsavel(Long id, Projeto projeto, Responsavel responsavel, Date dtRegistro) {
        this.id = id;
        this.projeto = projeto;
        this.responsavel = responsavel;
        this.dtRegistro = dtRegistro;
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
    public Date getDtRegistro() {
        return dtRegistro;
    }
    public void setDtRegistro(Date dtRegistro) {
        this.dtRegistro = dtRegistro;
    }


}
