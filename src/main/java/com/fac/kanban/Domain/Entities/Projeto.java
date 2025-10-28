package com.fac.kanban.Domain.Entities;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "projeto")
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "nome",  nullable = false, length = 60)
    private String nome;

    @Column(name = "status", nullable = false)
    private Integer status;
    
    @Column(name = "dt_inicio_previsto")
    private Date dtInicioPrevisto;
    
    @Column(name = "dt_termino_previsto")
    private Date dtTerminoPrevisto;
    
    @Column(name = "dt_inicio_realizado")
    private Date dtInicioRealizado;
    
    @Column(name = "dt_termino_realizado")
    private Date dtTerminoRealizado;

    @Column(name = "dias_atraso")
    private float diasAtraso;
    
    @Column(name = "perc_tempo_restante")
    private float percTempoRestante;
    
    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProjetoResponsavel> projetoResponsaveis = new HashSet<>();

    protected Projeto() { }

    public Projeto(Long id, String nome, Integer status, Date dtInicioPrevisto, Date dtTerminoPrevisto,
    Date dtInicioRealizado, Date dtTerminoRealizado, float diasAtraso, float percTempoRestante) {
        this.id = id;
        this.nome = nome;
        this.status = status;
        this.dtInicioPrevisto = dtInicioPrevisto;
        this.dtTerminoPrevisto = dtTerminoPrevisto;
        this.dtInicioRealizado = dtInicioRealizado;
        this.dtTerminoRealizado = dtTerminoRealizado;
        this.diasAtraso = diasAtraso;
        this.percTempoRestante = percTempoRestante;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getDtInicioPrevisto() {
        return dtInicioPrevisto;
    }

    public void setDtInicioPrevisto(Date dtInicioPrevisto) {
        this.dtInicioPrevisto = dtInicioPrevisto;
    }

    public Date getDtTerminoPrevisto() {
        return dtTerminoPrevisto;
    }

    public void setDtTerminoPrevisto(Date dtTerminoPrevisto) {
        this.dtTerminoPrevisto = dtTerminoPrevisto;
    }

    public Date getDtInicioRealizado() {
        return dtInicioRealizado;
    }

    public void setDtInicioRealizado(Date dtInicioRealizado) {
        this.dtInicioRealizado = dtInicioRealizado;
    }

    public Date getDtTerminoRealizado() {
        return dtTerminoRealizado;
    }

    public void setDtTerminoRealizado(Date dtTerminoRealizado) {
        this.dtTerminoRealizado = dtTerminoRealizado;
    }

    public float getDiasAtraso() {
        return diasAtraso;
    }

    public void setDiasAtraso(float diasAtraso) {
        this.diasAtraso = diasAtraso;
    }

    public float getPercTempoRestante() {
        return percTempoRestante;
    }

    public void setPercTempoRestante(float percTempoRestante) {
        this.percTempoRestante = percTempoRestante;
    }


}
