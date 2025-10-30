package com.fac.kanban.Application.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fac.kanban.Application.DTOs.ProjetoNovoDto;
import com.fac.kanban.Application.Exceptions.ApiCustomException;
import com.fac.kanban.Application.Exceptions.ApiResponse;
import com.fac.kanban.Domain.Entities.Projeto;
import com.fac.kanban.Domain.Enums.StatusEnums;
import com.fac.kanban.Domain.Repositories.IProjetoRepository;

@Service
public class ProjetoService implements IProjetoService {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(ProjetoService.class);

    @Autowired
    private IProjetoRepository projetosRepository;

    public ProjetoService(IProjetoRepository projetosRepository) {
        this.projetosRepository = projetosRepository;
    }

    @Override
    public ApiResponse<Projeto> criarProjeto(ProjetoNovoDto projetoNovoDto) {

        Projeto projeto = new Projeto();

        //=========== Validações ===========
        if (projetoNovoDto.getNome() == null || projetoNovoDto.getNome().isEmpty()) {
            throw new ApiCustomException(HttpStatus.BAD_REQUEST, "O nome do projeto não pode ser nulo ou vazio.");
        }

        if (!StatusEnums.isValid(projetoNovoDto.getStatus())) {
            throw new ApiCustomException(HttpStatus.BAD_REQUEST, "Status do projeto inválido.");
        }
        //=========== fim Validações ===========

        try {

            projeto.setId(projetoNovoDto.getId());
            projeto.setNome(projetoNovoDto.getNome());
            projeto.setStatus(projetoNovoDto.getStatus());
            projeto.setDtInicioPrevisto(projetoNovoDto.getDtInicioPrevisto());
            projeto.setDtTerminoPrevisto(projetoNovoDto.getDtTerminoPrevisto());
            projeto.setDtInicioRealizado(projetoNovoDto.getDtInicioRealizado());
            projeto.setDtTerminoRealizado(projetoNovoDto.getDtTerminoRealizado());
            projeto.setDiasAtraso(projetoNovoDto.getDiasAtraso());
            projeto.setPercTempoRestante(projetoNovoDto.getPercTempoRestante());

            projetosRepository.save(projeto);

            List<Projeto> projetos = new ArrayList<>();
            projetos.add(projeto);

            logger.info("Projeto '{}' criado com sucesso. ID: {}", projeto.getNome(), projeto.getId());

            return new ApiResponse<>(HttpStatus.CREATED, "Projeto criado com sucesso.", projetos);
            //return projeto;

        } catch (ApiCustomException ex) {
            logger.warn("Erro de validação ao criar projeto: {}", ex.getMessage());
            throw ex;

        } catch (Exception ex) {
            logger.error("Erro ao criar projeto: {}", ex.getMessage());
            throw new ApiCustomException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao criar projeto.");
        }
    }

    @Override
    public ApiResponse<Projeto> buscarProjetoPorId(Long id) {

        Projeto projeto = projetosRepository.findById(id).orElse(null);

        if (projeto == null) {
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "Projeto não encontrado para o ID: " + id, null);
        }
        List<Projeto> projetos = new ArrayList<>();
        projetos.add(projeto);
        return new ApiResponse<>(HttpStatus.OK, "Projeto encontrado com sucesso.", projetos);

    }

    @Override
    public ApiResponse<Projeto> listarProjetos() {
        List<Projeto> projetos = projetosRepository.findAll();
        return new ApiResponse<>(HttpStatus.OK, "Lista de projetos recuperada com sucesso.", projetos);
    }

    @Override
    public ApiResponse<Projeto> atualizarProjeto( ProjetoNovoDto projetoNovoDto) {

        Projeto projeto = new Projeto();

        var pjAtual = projetosRepository.findById(projetoNovoDto.getId()).orElse(null);
        if (pjAtual == null) {
            throw new ApiCustomException(HttpStatus.NOT_FOUND, "Projeto não encontrado para o ID: " + projetoNovoDto.getId());
        }
        //=========== Validações ===========
        if (!StatusEnums.isValid(projetoNovoDto.getStatus())) {
            throw new ApiCustomException(HttpStatus.BAD_REQUEST, "Status do projeto inválido.");
        }

        if( projetoNovoDto.getNome() == null || projetoNovoDto.getNome().isEmpty()) {
            throw new ApiCustomException(HttpStatus.BAD_REQUEST, "O nome do projeto não pode ser nulo ou vazio.");
        }
        //=========== fim Validações ===========

        if(projetoNovoDto.getStatus() == 1 && projetoNovoDto.getDtInicioRealizado() != null) {
            projetoNovoDto.setDtInicioRealizado(null);
        }

        if(projetoNovoDto.getStatus() == 2 && projetoNovoDto.getDtInicioRealizado() == null) {
            projetoNovoDto.setDtInicioRealizado(new Date());
        }
        
        projeto.setId(projetoNovoDto.getId());
        projeto.setNome(projetoNovoDto.getNome());
        projeto.setStatus(projetoNovoDto.getStatus()); 
        projeto.setDtInicioPrevisto(projetoNovoDto.getDtInicioPrevisto());
        projeto.setDtTerminoPrevisto(projetoNovoDto.getDtTerminoPrevisto());
        projeto.setDtInicioRealizado(projetoNovoDto.getDtInicioRealizado());
        projeto.setDtTerminoRealizado(projetoNovoDto.getDtTerminoRealizado());  
        projeto.setDiasAtraso(projetoNovoDto.getDiasAtraso());
        projeto.setPercTempoRestante(projetoNovoDto.getPercTempoRestante());
        
        projetosRepository.save(projeto);
        
        logger.info("Projeto '{}' atualizado com sucesso. ID: {}", projeto.getNome(), projeto.getId());
        
        List<Projeto> projetos = new ArrayList<>();
        projetos.add(projeto);

        return new ApiResponse<>(HttpStatus.OK, "Projeto atualizado com sucesso.", projetos);
    }

    @Override
    public ApiResponse<Void> deletarProjeto(Long id) {

        if (!projetosRepository.existsById(id)) {
            throw new ApiCustomException(HttpStatus.NOT_FOUND, "Projeto não encontrado para o ID: " + id);
        }

        //TODO: Verificar se há dependências antes de deletar (Responsáveis)

        projetosRepository.deleteById(id);

        return new ApiResponse<>(HttpStatus.OK, "Projeto deletado com sucesso.", null);
    }

}
