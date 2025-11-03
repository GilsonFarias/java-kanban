package com.fac.kanban.Application.DTOs;

import java.time.LocalDateTime;
import java.util.List;

import com.fac.kanban.Domain.Entities.Responsavel;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;

public class ProjetoDto {

    @NotNull(message = "O ID do projeto é obrigatório.", groups = OnUpdate.class )
    private Long id;

    @NotNull(message = "O nome do projeto é obrigatório.")
    private String nome;

    @NotNull(message = "O status do projeto é obrigatório.")
    private Integer status;

    @NotNull(message = "A data de início prevista é obrigatória.")
    private LocalDateTime dtInicioPrevisto;

    @NotNull(message = "A data de término prevista é obrigatória.") 
    private LocalDateTime dtTerminoPrevisto;
    
    private LocalDateTime dtInicioRealizado;

    private LocalDateTime dtTerminoRealizado;

    private float diasAtraso;
    
    private float percTempoRestante;

    private List<Responsavel> responsaveis;

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

    public LocalDateTime getDtInicioPrevisto() {
        return dtInicioPrevisto;
    }

    public void setDtInicioPrevisto(LocalDateTime  dtInicioPrevisto) {
        this.dtInicioPrevisto = dtInicioPrevisto;
    }

    public LocalDateTime getDtTerminoPrevisto() {
        return dtTerminoPrevisto;
    }

    public void setDtTerminoPrevisto(LocalDateTime dtTerminoPrevisto) {
        this.dtTerminoPrevisto = dtTerminoPrevisto;
    }

    public LocalDateTime getDtInicioRealizado() {
        return dtInicioRealizado;
    }

    public void setDtInicioRealizado(LocalDateTime dtInicioRealizado) {
        this.dtInicioRealizado = dtInicioRealizado;
    }

    public LocalDateTime getDtTerminoRealizado() {
        return dtTerminoRealizado;
    }

    public void setDtTerminoRealizado(LocalDateTime dtTerminoRealizado) {
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

    public List<Responsavel> getResponsaveis() {
        return responsaveis;
    }

    public void setResponsaveis(List<Responsavel> responsaveis) {
        this.responsaveis = responsaveis;
    }

    public ProjetoDto() {
    }

    public ProjetoDto( Long Id, String nome, Integer status, LocalDateTime dtInicioPrevisto, LocalDateTime dtTerminoPrevisto, LocalDateTime dtInicioRealizado, LocalDateTime dtTerminoRealizado, float diasAtraso, float percTempoRestante, List<Responsavel> responsaveis) {
        this.id = Id;
        this.nome = nome;
        this.status = status;
        this.dtInicioPrevisto = dtInicioPrevisto;
        this.dtTerminoPrevisto = dtTerminoPrevisto;
        this.dtInicioRealizado = dtInicioRealizado;
        this.dtTerminoRealizado = dtTerminoRealizado;
        this.diasAtraso = diasAtraso;
        this.percTempoRestante = percTempoRestante;
        this.responsaveis = responsaveis;
    }
    
    // Interfaces para grupos de validação
    public interface OnCreate extends Default {
    }

    public interface OnUpdate extends Default {
    }

}
