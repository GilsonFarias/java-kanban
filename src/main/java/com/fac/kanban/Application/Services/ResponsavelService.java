package com.fac.kanban.Application.Services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fac.kanban.Application.DTOs.ResponsavelDto;
import com.fac.kanban.Application.Exceptions.ApiCustomException;
import com.fac.kanban.Application.Exceptions.ApiResponse;
import com.fac.kanban.Domain.Entities.Responsavel;
import com.fac.kanban.Domain.Repositories.IResponsavelRepository;

@Service
public class ResponsavelService implements IResponsavelService {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(ProjetoService.class);

    @Autowired
    private IResponsavelRepository responsavelRepository;

    public ResponsavelService(IResponsavelRepository responsavelRepository) {
        this.responsavelRepository = responsavelRepository;
    }

    @Override
    public ApiResponse<Responsavel> criarResponsavel(ResponsavelDto responsavelDto) {

        Responsavel responsavel = new Responsavel();

        // =========== Validações ===========
        if (responsavelDto.getNome() == null || responsavelDto.getNome().isEmpty()) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST, "O nome do responsável não pode ser nulo ou vazio.", null);
        }

        try {
            responsavel.setNome(responsavelDto.getNome());
            responsavel.setEmail(responsavelDto.getEmail());
            responsavel.setCargo(responsavelDto.getCargo());

            List<Responsavel> responsaveis = new ArrayList<>();
            responsaveis.add(responsavel);

            responsavelRepository.save(responsavel);
            return new ApiResponse<>(HttpStatus.CREATED, "Responsável criado com sucesso.", responsaveis);
        } catch (Exception ex) {
            logger.error("Erro ao criar responsável: {}", ex.getMessage());
            throw new ApiCustomException(HttpStatus.BAD_REQUEST, "Erro ao criar responsável.");
        }
    }

    @Override
    public ApiResponse<Responsavel> buscarResponsavelPorId(Long id) {
        Responsavel responsavel = responsavelRepository.findById(id).orElse(null);

        if (responsavel == null) {
            throw new ApiCustomException(HttpStatus.NOT_FOUND, "Responsável não encontrado.");
        }
        List<Responsavel> responsaveis = new ArrayList<>();
        responsaveis.add(responsavel);
        return new ApiResponse<>(HttpStatus.OK, "Responsável encontrado com sucesso.", responsaveis);
    }

    @Override
    public ApiResponse<Responsavel> listarResponsaveis() {
        List<Responsavel> responsaveis = responsavelRepository.findAll();
        return new ApiResponse<>(HttpStatus.OK, "Lista de responsáveis obtida com sucesso.", responsaveis);
    }

    @Override
    public ApiResponse<Responsavel> atualizarResponsavel(ResponsavelDto responsavelDto) {

        if (!responsavelRepository.existsById(responsavelDto.getId())) {
            throw new ApiCustomException(HttpStatus.NOT_FOUND,
                    "Responsável não encontrado para o ID: " + responsavelDto.getId());
        }

        Responsavel responsavel = new Responsavel();

        responsavel.setId(responsavelDto.getId());
        responsavel.setNome(responsavelDto.getNome());
        responsavel.setEmail(responsavelDto.getEmail());
        responsavel.setCargo(responsavelDto.getCargo());
        responsavelRepository.save(responsavel);

        List<Responsavel> responsaveis = new ArrayList<>();
        responsaveis.add(responsavel);

        return new ApiResponse<>(HttpStatus.OK, "Responsável atualizado com sucesso.", responsaveis);

    }

    @Override
    public ApiResponse<Void> deletarResponsavel(Long id) {
        if (!responsavelRepository.existsById(id)) {
            throw new ApiCustomException(HttpStatus.NOT_FOUND, "Responsável não encontrado para o ID: " + id);
        }
        responsavelRepository.deleteById(id);
        return new ApiResponse<>(HttpStatus.NO_CONTENT, "Responsável deletado com sucesso.", null);
    }

}
