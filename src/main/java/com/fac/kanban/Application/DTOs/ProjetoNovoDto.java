package com.fac.kanban.Application.DTOs;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;

import java.util.Date;

public class ProjetoNovoDto {

    @NotNull(message = "O ID do projeto é obrigatório.", groups = OnUpdate.class )
    private Long id;

    @NotNull(message = "O nome do projeto é obrigatório.")
    private String nome;

    @NotNull(message = "O status do projeto é obrigatório.")
    private Integer status;

    @NotNull(message = "A data de início prevista é obrigatória.")
    private Date dtInicioPrevisto;

    @NotNull(message = "A data de término prevista é obrigatória.") 
    private Date dtTerminoPrevisto;
    
    private Date dtInicioRealizado;

    private Date dtTerminoRealizado;

    private float diasAtraso;
    
    private float percTempoRestante;

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

    public ProjetoNovoDto() {
    }

    public ProjetoNovoDto( Long Id, String nome, Integer status, Date dtInicioPrevisto, Date dtTerminoPrevisto, Date dtInicioRealizado, Date dtTerminoRealizado, float diasAtraso, float percTempoRestante) {
        this.id = Id;
        this.nome = nome;
        this.status = status;
        this.dtInicioPrevisto = dtInicioPrevisto;
        this.dtTerminoPrevisto = dtTerminoPrevisto;
        this.dtInicioRealizado = dtInicioRealizado;
        this.dtTerminoRealizado = dtTerminoRealizado;
        this.diasAtraso = diasAtraso;
        this.percTempoRestante = percTempoRestante;
    }
    
    // Interfaces para grupos de validação
    public interface OnCreate extends Default {
    }

    public interface OnUpdate extends Default {
    }
}
