package com.fac.kanban.Application.DTOs;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;

public class ResponsavelDto {

    @NotNull(message = "O ID do projeto é obrigatório.", groups = OnUpdate.class )
    private Long id;

    @NotNull(message = "O nome do responsável é obrigatório.")
    private String nome;

    @NotNull(message = "O email do responsável é obrigatório.")
    private String email;

    @NotNull(message = "O cargo do responsável é obrigatório.")    
    private String cargo;

    public ResponsavelDto() {}

    public ResponsavelDto(Long id, String nome, String email, String cargo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cargo = cargo;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    // Interfaces para grupos de validação
    public interface OnCreate extends Default {
    }

    public interface OnUpdate extends Default {
    }

}
