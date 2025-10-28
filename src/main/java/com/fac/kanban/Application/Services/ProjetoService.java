package com.fac.kanban.Application.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fac.kanban.Domain.Entities.Projeto;
import com.fac.kanban.Domain.Repositories.IProjetoRepository;

@Service
public class ProjetoService implements IProjetoService {

    @Autowired
    private IProjetoRepository projetosRepository;

    public ProjetoService(IProjetoRepository projetosRepository) {
        this.projetosRepository = projetosRepository;
    }

    @Override
    public void criarProjeto(Projeto projeto) {
        projetosRepository.save(projeto);
    }

    @Override
    public Projeto buscarProjetoPorId(Long id) {
        return projetosRepository.findById(id).orElse(null);
    }

    @Override
    public List<Projeto> listarProjetos() {
        return projetosRepository.findAll();
    }

    @Override
    public void atualizarProjeto(Long id, Projeto projeto) {
        if (projetosRepository.existsById(id)) {
            projeto.setId(id);
            projetosRepository.save(projeto);
        }
    }

    @Override
    public void deletarProjeto(Long id) {
        projetosRepository.deleteById(id);
    }

}
