package com.fac.kanban.Domain.Enums;

public enum StatusEnums {

    A_iniciar( 1, "A iniciar"),
    Em_andamento( 2, "Em andamento"),
    Atrasado( 3, "Atrasado"),
    Concluido( 4, "Concluído");

    private final int id;
    private final String descricao;
    
    StatusEnums( int id, String descricao){
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
}
