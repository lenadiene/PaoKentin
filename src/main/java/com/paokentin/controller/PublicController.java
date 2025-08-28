package com.paokentin.controller;

import com.paokentin.dto.FornadaStatusDTO;
import com.paokentin.model.Fornada;
import com.paokentin.model.Pao;
import com.paokentin.repository.FornadaRepository;
import com.paokentin.repository.PaoRepository;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/public")
@CrossOrigin
public class PublicController {

    private final PaoRepository paoRepo;
    private final FornadaRepository fornadaRepo;
    private static final DateTimeFormatter ISO = DateTimeFormatter.ISO_DATE_TIME;

    public PublicController(PaoRepository paoRepo, FornadaRepository fornadaRepo) {
        this.paoRepo = paoRepo;
        this.fornadaRepo = fornadaRepo;
    }

    @GetMapping("/ultimas")
    public List<FornadaStatusDTO> ultimas() {
        List<FornadaStatusDTO> out = new ArrayList<>();
        List<Pao> paes = paoRepo.findAll();
        LocalDateTime agora = LocalDateTime.now(ZoneId.systemDefault());
        for (Pao p : paes) {
            Optional<Fornada> fOpt = fornadaRepo.findUltimaByPaoId(p.getId());
            FornadaStatusDTO dto = new FornadaStatusDTO();
            dto.paoId = p.getId();
            dto.descricao = p.getDescricao();
            dto.corHex = p.getCorHex();
            dto.tempoPreparoMinutos = p.getTempoPreparoMinutos();

            if (fOpt.isEmpty()) {
                dto.status = "SEM_FORNADA";
                dto.segundosRestantes = null;
                dto.inicioIso = null;
            } else {
                Fornada f = fOpt.get();
                dto.inicioIso = f.getInicio().format(ISO);
                LocalDateTime pronto = f.getInicio().plusMinutes(p.getTempoPreparoMinutos());
                if (agora.isBefore(pronto)) {
                    dto.status = "EM_PRODUCAO";
                    dto.segundosRestantes = Duration.between(agora, pronto).getSeconds();
                } else {
                    dto.status = "PRONTO";
                    dto.segundosRestantes = 0L;
                }
            }
            out.add(dto);
        }
        return out;
    }
}
