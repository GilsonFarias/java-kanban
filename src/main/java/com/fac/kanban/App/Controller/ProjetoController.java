package com.fac.kanban.App.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fac.kanban.Application.DTOs.ProjetoDto;
import com.fac.kanban.Application.DTOs.ProjetoNovoDto;
import com.fac.kanban.Application.Exceptions.ApiResponse;
import com.fac.kanban.Application.Services.IProjetoService;
import com.fac.kanban.Domain.Entities.Projeto;

@RestController
@RequestMapping("api/projetos")
public class ProjetoController {
    @Autowired
    private IProjetoService projetoService;
    
    @GetMapping("/listar")
    public ResponseEntity<ApiResponse<ProjetoDto>> getAllProjetos() {

        return ResponseEntity.ok(projetoService.listarProjetos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProjetoDto>> getProjetoById(@PathVariable Long id) {

        return ResponseEntity.ok(projetoService.buscarProjetoPorId(id));
    }

    @PostMapping("/criar")
    public ResponseEntity<ApiResponse<ProjetoDto>> createProjeto(
        @RequestBody @Validated(ProjetoNovoDto.OnCreate.class) ProjetoDto projetoDto) {

        var projeto = projetoService.criarProjeto(projetoDto);

        return ResponseEntity.ok(projeto);
    }

    @PostMapping("/atualizar")
    public ResponseEntity<ApiResponse<Projeto>> updateProjeto(
            @RequestBody @Validated(ProjetoNovoDto.OnUpdate.class) ProjetoDto projetoDto) {

        var projeto = projetoService.atualizarProjeto( projetoDto);

        return ResponseEntity.ok(projeto);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProjeto(@PathVariable Long id) {

        var resultado = projetoService.deletarProjeto(id);
        
        return ResponseEntity.ok(resultado);
    }

}
