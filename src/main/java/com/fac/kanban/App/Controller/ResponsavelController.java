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

import com.fac.kanban.Application.DTOs.ResponsavelDto;
import com.fac.kanban.Application.Exceptions.ApiResponse;
import com.fac.kanban.Application.Services.IResponsavelService;
import com.fac.kanban.Domain.Entities.Responsavel;

@RestController
@RequestMapping("api/responsaveis")
public class ResponsavelController {
    @Autowired
    private IResponsavelService responsavelService;

    @GetMapping("/listar")
    public ResponseEntity<ApiResponse<Responsavel>> getAllResponsaveis() {

        return ResponseEntity.ok(responsavelService.listarResponsaveis());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Responsavel>> getResponsavelById(@PathVariable Long id) {

        return ResponseEntity.ok(responsavelService.buscarResponsavelPorId(id));
    }

    @PostMapping("/criar")
    public ResponseEntity<ApiResponse<Responsavel>> createResponsavel(
        @RequestBody @Validated(ResponsavelDto.OnCreate.class) ResponsavelDto responsavelDto) {

        var responsavel = responsavelService.criarResponsavel(responsavelDto);

        return ResponseEntity.ok(responsavel);
    }

    @PostMapping("/atualizar")
    public ResponseEntity<ApiResponse<Responsavel>> updateResponsavel(
            @RequestBody @Validated(ResponsavelDto.OnUpdate.class) ResponsavelDto responsavelDto) {

        var responsavel = responsavelService.atualizarResponsavel(responsavelDto);

        return ResponseEntity.ok(responsavel);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteResponsavel(@PathVariable Long id) {

        var resultado = responsavelService.deletarResponsavel(id);
        
        return ResponseEntity.ok(resultado);
    }

}
