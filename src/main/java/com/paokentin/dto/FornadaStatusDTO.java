package com.paokentin.dto;

public class FornadaStatusDTO {
    public Long paoId;
    public String descricao;
    public String corHex;
    public Integer tempoPreparoMinutos;
    public String inicioIso; // ISO-8601 (server time)
    public String status;    // EM_PRODUCAO | PRONTO | SEM_FORNADA
    public Long segundosRestantes;

    public FornadaStatusDTO() {}
}
