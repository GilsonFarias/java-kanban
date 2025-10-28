package com.fac.kanban.App.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fac.kanban.Application.Services.IResponsavelService;
import com.fac.kanban.Domain.Entities.Responsavel;

@RestController
@RequestMapping("api/responsaveis")
public class ResponsavelController {
    @Autowired
    private IResponsavelService responsavelService;

    @GetMapping("/listar")
    public List<Responsavel> getAllResponsaveis() {
        return responsavelService.listarResponsaveis();
    }

}
