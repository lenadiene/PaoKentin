package com.paokentin.controller;

import com.paokentin.model.Pao;
import com.paokentin.repository.PaoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paes")
@CrossOrigin
public class PaoController {

    private final PaoRepository paoRepository;

    public PaoController(PaoRepository paoRepository) {
        this.paoRepository = paoRepository;
    }

    @PostMapping
    public ResponseEntity<Pao> create(@RequestBody Pao p) {
        if (p.getDescricao() == null || p.getTempoPreparoMinutos() == null) {
            return ResponseEntity.badRequest().build();
        }
        if (p.getCorHex() == null || p.getCorHex().isBlank()) {
            p.setCorHex("#cccccc");
        }
        return ResponseEntity.ok(paoRepository.save(p));
    }

    @GetMapping
    public List<Pao> list() {
        return paoRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Pao> detail(@PathVariable Long id) {
        return paoRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
