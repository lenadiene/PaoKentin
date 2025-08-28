package com.paokentin.model;

public class Pao {
    private Long id;
    private String descricao;
    private Integer tempoPreparoMinutos;
    private String corHex;

    public Pao() {}
    public Pao(Long id, String descricao, Integer tempoPreparoMinutos, String corHex) {
        this.id = id;
        this.descricao = descricao;
        this.tempoPreparoMinutos = tempoPreparoMinutos;
        this.corHex = corHex;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Integer getTempoPreparoMinutos() { return tempoPreparoMinutos; }
    public void setTempoPreparoMinutos(Integer tempoPreparoMinutos) { this.tempoPreparoMinutos = tempoPreparoMinutos; }
    public String getCorHex() { return corHex; }
    public void setCorHex(String corHex) { this.corHex = corHex; }
}
