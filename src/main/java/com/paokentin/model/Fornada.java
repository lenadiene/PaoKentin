package com.paokentin.model;

import java.time.LocalDateTime;

public class Fornada {
    private Long id;
    private Long paoId;
    private LocalDateTime inicio;

    public Fornada() {}
    public Fornada(Long id, Long paoId, LocalDateTime inicio) {
        this.id = id;
        this.paoId = paoId;
        this.inicio = inicio;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getPaoId() { return paoId; }
    public void setPaoId(Long paoId) { this.paoId = paoId; }
    public LocalDateTime getInicio() { return inicio; }
    public void setInicio(LocalDateTime inicio) { this.inicio = inicio; }
}
