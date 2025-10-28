package com.fac.kanban.Application.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fac.kanban.Domain.Entities.Responsavel;
import com.fac.kanban.Domain.Repositories.IResponsavelRepository;

@Service
public class ResponsavelService implements IResponsavelService {

    @Autowired
    private IResponsavelRepository responsavelRepository;

    public ResponsavelService(IResponsavelRepository responsavelRepository) {
        this.responsavelRepository = responsavelRepository;
    }

    @Override
    public List<Responsavel> listarResponsaveis() {
        return responsavelRepository.findAll();
    }

    @Override
    public void criarResponsavel(Responsavel responsavel) {
        responsavelRepository.save(responsavel);
    }
    @Override
    public Responsavel buscarResponsavelPorId(Long id) {
        return responsavelRepository.findById(id).orElse(null);
    }

    @Override
    public void atualizarResponsavel(Long id, Responsavel responsavel) {
        if (responsavelRepository.existsById(id)) {
            responsavel.setId(id);
            responsavelRepository.save(responsavel);
        }
    }
    
    @Override
    public void deletarResponsavel(Long id) {  
        responsavelRepository.deleteById(id);
    }

}
