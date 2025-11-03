package com.fac.kanban.Application.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fac.kanban.Application.DTOs.ProjetoDto;
import com.fac.kanban.Application.Exceptions.ApiCustomException;
import com.fac.kanban.Application.Exceptions.ApiResponse;
import com.fac.kanban.Application.Validations.ProjetoValidatorService;
import com.fac.kanban.Domain.Entities.Projeto;
import com.fac.kanban.Domain.Entities.ProjetoResponsavel;
import com.fac.kanban.Domain.Entities.Responsavel;
import com.fac.kanban.Domain.Repositories.IProjetoRepository;
import com.fac.kanban.Domain.Repositories.IProjetoResponsavelRepository;
import com.fac.kanban.Domain.Repositories.IResponsavelRepository;

import jakarta.transaction.Transactional;

@Service
public class ProjetoService implements IProjetoService {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(ProjetoService.class);

    @Autowired
    private IProjetoRepository projetosRepository;
    private IProjetoResponsavelRepository projetoResponsavelRepository;
    private IResponsavelRepository responsavelRepository;
    private ProjetoValidatorService projetoValidatorService;

    public ProjetoService(IProjetoRepository projetosRepository,
            IProjetoResponsavelRepository projetoResponsavelRepository,
            IResponsavelRepository responsavelRepository,
            ResponsavelService responsavelService,
            ProjetoValidatorService projetoValidatorService) {
        this.projetosRepository = projetosRepository;
        this.projetoResponsavelRepository = projetoResponsavelRepository;
        this.responsavelRepository = responsavelRepository;
        this.projetoValidatorService = projetoValidatorService;
    }

    @Override
    @Transactional
    public ApiResponse<ProjetoDto> criarProjeto(ProjetoDto projetoDto) {

        Projeto projeto = new Projeto();

        projetoValidatorService.validatorProjeto(projetoDto, "criar");

        try {

            //projeto.setId(projetoDto.getId());
            projeto.setNome(projetoDto.getNome());
            projeto.setStatus(1);
            projeto.setDtInicioPrevisto(projetoDto.getDtInicioPrevisto());
            projeto.setDtTerminoPrevisto(projetoDto.getDtTerminoPrevisto());
            projeto.setDtInicioRealizado(null);
            projeto.setDtTerminoRealizado(null);
            projeto.setDiasAtraso(0);
            projeto.setPercTempoRestante(0);

            projetosRepository.save(projeto);

            projetoResponsavelRepository.deleteByProjeto_Id(projeto.getId());

            if (projetoDto.getResponsaveis() != null) {

                for (Responsavel responsavelDto : projetoDto.getResponsaveis()) {

                    var responsavelExiste = responsavelRepository.findById(responsavelDto.getId()).orElse(null);
                    if (responsavelExiste == null) {
                        throw new ApiCustomException(HttpStatus.BAD_REQUEST, "Responsavel não estar cadastrado.");
                    }

                    ProjetoResponsavel projetoResponsavel = new ProjetoResponsavel();

                    projetoResponsavel.setProjetoId(projeto);
                    projetoResponsavel.setResponsavel(responsavelDto);
                    projetoResponsavel.setDtRegistro(new Date());

                    projetoResponsavelRepository.save(projetoResponsavel);
                }

            }

            logger.info("Projeto '{}' criado com sucesso. ID: {}", projeto.getNome(), projeto.getId());

            ApiResponse<ProjetoDto> dto = buscarProjetoPorId(projeto.getId());

            return new ApiResponse<>(HttpStatus.CREATED, "Projeto criado com sucesso.", dto.getData());

        } catch (ApiCustomException ex) {
            logger.warn("Erro de validação ao criar projeto: {}", ex.getMessage());
            throw ex;

        } catch (Exception ex) {
            logger.error("Erro ao criar projeto: {}", ex.getMessage());
            throw new ApiCustomException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao criar projeto.");
        }
    }

    @Override
    public ApiResponse<ProjetoDto> buscarProjetoPorId(Long id) {

        Projeto projeto = projetosRepository.findById(id).orElse(null);

        if (projeto == null) {
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "Projeto não encontrado para o ID: " + id, null);
        }

        var pRRepository = projetoResponsavelRepository.findByProjeto_Id(projeto.getId());

        List<Responsavel> responsaveis = new ArrayList<>();

        for (ProjetoResponsavel pr : pRRepository) {
            var resp = responsavelRepository.findById(pr.getResponsavel().getId()).orElse(null);
            if (resp != null) {
                responsaveis.add(new Responsavel(resp.getId(), resp.getNome(), resp.getEmail(), resp.getCargo()));
            }
        }

        List<ProjetoDto> projetoDto = new ArrayList<>();
        projetoDto.add(new ProjetoDto(
                projeto.getId(),
                projeto.getNome(),
                projeto.getStatus(),
                projeto.getDtInicioPrevisto(),
                projeto.getDtTerminoPrevisto(),
                projeto.getDtInicioRealizado(),
                projeto.getDtTerminoRealizado(),
                projeto.getDiasAtraso(),
                projeto.getPercTempoRestante(),
                responsaveis));

        return new ApiResponse<>(HttpStatus.OK, "Projeto encontrado com sucesso.", projetoDto);

    }

    @Override
    public ApiResponse<ProjetoDto> listarProjetos() {
        List<Projeto> projetos = projetosRepository.findAll();
        List<ProjetoDto> projetoDtos = new ArrayList<>();

        for (Projeto projeto : projetos) {

        var pRRepository = projetoResponsavelRepository.findByProjeto_Id(projeto.getId());

        List<Responsavel> responsaveis = new ArrayList<>();

        for (ProjetoResponsavel pr : pRRepository) {
            var resp = responsavelRepository.findById(pr.getResponsavel().getId()).orElse(null);
            if (resp != null) {
                responsaveis.add(new Responsavel(resp.getId(), resp.getNome(), resp.getEmail(), resp.getCargo()));
            }
        }
            projetoDtos.add( new ProjetoDto(
                projeto.getId(),
                projeto.getNome(),
                projeto.getStatus(),
                projeto.getDtInicioPrevisto(),
                projeto.getDtTerminoPrevisto(),
                projeto.getDtInicioRealizado(),
                projeto.getDtTerminoRealizado(),
                projeto.getDiasAtraso(),
                projeto.getPercTempoRestante(),
                responsaveis
                ));
            }

        return new ApiResponse<>(HttpStatus.OK, "Lista de projetos recuperada com sucesso.", projetoDtos);
    }

    @Override
    @Transactional
    public ApiResponse<Projeto> atualizarProjeto(ProjetoDto projetoDto) {
        //String msg = "";
        Projeto projeto = new Projeto();
        
        projetoValidatorService.validatorProjeto(projetoDto, "editar");

        Projeto projetoAtual = projetosRepository.findById(projetoDto.getId()).orElse(null);

        if( projetoAtual.getStatus() != projetoDto.getStatus() ){
            projetoValidatorService.validatorStatus(projetoDto, projetoAtual, "editar");
        }

        projeto.setId(projetoDto.getId());
        projeto.setNome(projetoDto.getNome());
        projeto.setStatus(projetoDto.getStatus());
        projeto.setDtInicioPrevisto(projetoDto.getDtInicioPrevisto());
        projeto.setDtTerminoPrevisto(projetoDto.getDtTerminoPrevisto());
        projeto.setDtInicioRealizado(projetoDto.getDtInicioRealizado());
        projeto.setDtTerminoRealizado(projetoDto.getDtTerminoRealizado());
        projeto.setDiasAtraso(projetoDto.getDiasAtraso());
        projeto.setPercTempoRestante(projetoDto.getPercTempoRestante());

        projetosRepository.save(projeto);

        projetoResponsavelRepository.deleteByProjeto_Id(projeto.getId());

        if (projetoDto.getResponsaveis() != null) {

            for (Responsavel responsavelDto : projetoDto.getResponsaveis()) {

                var responsavelExiste = responsavelRepository.findById(responsavelDto.getId()).orElse(null);
                if (responsavelExiste == null) {
                    throw new ApiCustomException(HttpStatus.BAD_REQUEST, "Responsavel não estar cadastrado.");
                }

                ProjetoResponsavel projetoResponsavel = new ProjetoResponsavel();

                projetoResponsavel.setProjetoId(projeto);
                projetoResponsavel.setResponsavel(responsavelDto);
                projetoResponsavel.setDtRegistro(new Date());

                projetoResponsavelRepository.save(projetoResponsavel);
            }
        }

        logger.info("Projeto '{}' atualizado com sucesso. ID: {}", projeto.getNome(), projeto.getId());

        List<Projeto> projetos = new ArrayList<>();
        projetos.add(projeto);

        return new ApiResponse<>(HttpStatus.OK, "Projeto atualizado com sucesso.", projetos);
    }

    @Override
    public ApiResponse<Void> deletarProjeto(Long id) {
        String msg = "";

        if (projetosRepository.findById(id).orElse(null) == null) {
            logger.info( msg = "Projeto não encontrado para o ID: " + id );
            throw new ApiCustomException(HttpStatus.NOT_FOUND, msg );
        }

        if (projetoResponsavelRepository.findById(id).orElse(null) == null) {
            logger.info(msg = "Remova os responsáveis");
            throw new ApiCustomException(HttpStatus.NOT_FOUND, msg);
        }

        projetosRepository.deleteById(id);

        logger.info(msg = "Projeto deletado com sucesso.");
        return new ApiResponse<>(HttpStatus.OK, msg, null);
    }

}
