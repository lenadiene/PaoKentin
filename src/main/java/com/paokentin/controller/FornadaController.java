package com.paokentin.controller;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paokentin.model.Fornada;
import com.paokentin.repository.FornadaRepository;

@RestController
@RequestMapping("/api/fornadas")
@CrossOrigin
public class FornadaController {

    private final FornadaRepository repo;

    public FornadaController(FornadaRepository repo) {
        this.repo = repo;
    }

    @PostMapping("/{paoId}")
    public ResponseEntity<Fornada> registrar(@PathVariable("paoId") Long paoId) {
        Fornada f = new Fornada(null, paoId, LocalDateTime.now());
        return ResponseEntity.ok(repo.save(f));
    }
}
