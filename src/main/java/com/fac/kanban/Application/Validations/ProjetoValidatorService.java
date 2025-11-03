package com.fac.kanban.Application.Validations;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fac.kanban.Application.DTOs.ProjetoDto;
import com.fac.kanban.Application.Exceptions.ApiCustomException;
import com.fac.kanban.Application.Services.ProjetoService;
import com.fac.kanban.Domain.Entities.Projeto;
import com.fac.kanban.Domain.Enums.StatusEnums;
import com.fac.kanban.Domain.Repositories.IProjetoRepository;

@Service
public class ProjetoValidatorService {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(ProjetoService.class);

    private IProjetoRepository projetosRepository;
 
    public ProjetoValidatorService(IProjetoRepository projetoRepository) {
        this.projetosRepository = projetoRepository;
    }

    public void validatorProjeto( ProjetoDto projetoDto, String executar ) {
        String msg = "";

        if ( executar == "editar" && projetosRepository.findById(projetoDto.getId()).orElse(null) == null) {
            logger.info( msg = "Projeto não encontrado para o ID: " + projetoDto.getId() );
            throw new ApiCustomException( HttpStatus.NOT_FOUND, msg );
        }

        if (!StatusEnums.isValid(projetoDto.getStatus())) {
            throw new ApiCustomException(
                HttpStatus.BAD_REQUEST, "Status do projeto inválido.");
        }

        if ( projetoDto.getNome() == null || projetoDto.getNome().isEmpty()) {
            throw new ApiCustomException(
                HttpStatus.BAD_REQUEST, "O nome do projeto não pode ser nulo ou vazio.");
        }
    }


    public void validatorStatus(ProjetoDto projetoDto, Projeto projeto, String executar) {

        String statusCode = projeto.getStatus().toString() + projetoDto.getStatus().toString();

        switch (statusCode) {
            case "12" -> {// A iniciar -> Em_andamento
                projetoDto.setDtInicioRealizado(LocalDateTime.now());
            }

            case "13" -> { //A iniciar -> Atrasado
                if (LocalDateTime.now().getHour() < projetoDto.getDtInicioPrevisto().getHour()) {
                    throw new ApiCustomException(
                            HttpStatus.BAD_REQUEST, "Erro: Não pode marcar atraso antes do início previsto.");
                }
            }

            case "14" -> {// A iniciar -> Concluído
                projetoDto.setDtTerminoRealizado(LocalDateTime.now() );
            }

            case "21" -> {// Em andamento -> A iniciar
                projetoDto.setDtInicioRealizado(null);
            }

            case "23" -> {// Em andamento -> A Atraso ?????
                if (projetoDto.getDtInicioRealizado() != null ||
                        projetoDto.getDtInicioPrevisto() != null ||
                        projetoDto.getDtTerminoPrevisto() != null) {
                    throw new ApiCustomException(
                            HttpStatus.BAD_REQUEST, "Erro: Ou remova início Realizado " +
                                    "( volte a 'A iniciar' com atraso se cabível) " +
                                    "ou ajuste Início/Termino Previsto para hoje.");
                }
            }

            case "24", "34" -> {// (24 - Em andamento -> Concluído), (34 - Atrasado -> Concluído)
                projetoDto.setDtTerminoRealizado(LocalDateTime.now() );
            }

            case "31" -> {// Atrasado -> A iniciar
                LocalDateTime agora31 = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
                LocalDateTime inicioPrevisto31 = projetoDto.getDtInicioPrevisto().truncatedTo(ChronoUnit.MINUTES);
                LocalDateTime terminoPrevisto31 =  projetoDto.getDtTerminoPrevisto().truncatedTo(ChronoUnit.MINUTES);
                if (projetoDto.getDtInicioRealizado() != null ||
                        !inicioPrevisto31.isEqual(agora31) ||
                        !terminoPrevisto31.isEqual(agora31) ) {
                    throw new ApiCustomException(
                            HttpStatus.BAD_REQUEST,
                            "Erro: Remova início realizado e ajuste início/Termino previsto para a data atual.");
                }
            }

            case "32" -> {// Atrasado -> Em andamento
                if (projetoDto.getDtInicioPrevisto() != LocalDateTime.now() ||
                        projetoDto.getDtTerminoPrevisto() != LocalDateTime.now() ) {
                    throw new ApiCustomException(
                            HttpStatus.BAD_REQUEST, "Erro:Ajuste início/término previsto para a data atual.");
                }
                
            }

            case "41" -> { // Concluído -> A iniciar
                LocalDateTime agora = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
                LocalDateTime inicioPrevisto = projetoDto.getDtInicioPrevisto().truncatedTo(ChronoUnit.MINUTES);
                LocalDateTime terminoPrevisto =  projetoDto.getDtTerminoPrevisto().truncatedTo(ChronoUnit.MINUTES);
                if (projetoDto.getDtTerminoRealizado() != null ||
                        !inicioPrevisto.isEqual(agora) ||
                        !terminoPrevisto.isEqual(agora) ) {
                    throw new ApiCustomException(
                            HttpStatus.BAD_REQUEST,
                            "Erro: Remova término realizado e ajuste início/término previsto para a data atual.");
                }
                //break;
            }

            case "42" -> {// Concluído -> Em andamento
                if (LocalDateTime.now().getHour()  > projetoDto.getDtTerminoPrevisto().getHour()) {
                    throw new ApiCustomException(
                            HttpStatus.BAD_REQUEST, "Valid: Juste a data do término previsto.");
                }
                projetoDto.setDtTerminoRealizado(null);
            }

            case "43" -> {// Concluído -> Atrasado
                if (LocalDateTime.now().getHour() > projetoDto.getDtTerminoPrevisto().getHour()) {
                    projetoDto.setDtTerminoRealizado(null);
                } else {
                    throw new ApiCustomException(
                            HttpStatus.BAD_REQUEST, "Valid: Juste as datas.");
                }
            }
        }

    }

}
