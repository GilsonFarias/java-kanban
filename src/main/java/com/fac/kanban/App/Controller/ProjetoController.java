package com.fac.kanban.App.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fac.kanban.Application.Services.IProjetoService;
import com.fac.kanban.Domain.Entities.Projeto;

@RestController
@RequestMapping("api/projetos")
public class ProjetoController {
    @Autowired
    private IProjetoService projetoService;
    
    @GetMapping("/listar")
    public List<Projeto> getAllProjetos() {
        return projetoService.listarProjetos();
    }

}
