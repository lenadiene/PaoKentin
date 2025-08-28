# PãoKentin

Sistema de notificações para padarias: cadastro de pães, registro de fornadas e consulta pública das últimas fornadas com contagem regressiva.

## Como rodar (Back-end)

1. Requer: Java 17+ e Maven 3.9+
2. Na pasta `PaoKentin`, rode:
   ```bash
   ./mvnw spring-boot:run
   ```
3. A API sobe em `http://localhost:8080`

## Endpoints principais

- `POST /api/paes` — cadastra um pão
- `GET /api/paes` — lista pães
- `GET /api/paes/{id}` — detalhe do pão
- `POST /api/fornadas/{paoId}` — registra uma fornada "agora"
- `GET /api/public/ultimas` — retorna a última fornada por pão com status/contador

## Como rodar (Front-end)

Abra `PaoKentinFront/index.html` no navegador (ou sirva com um HTTP server simples).

No topo de cada página há um campo para informar a URL do back-end (padrão: `http://localhost:8080`).

## Banco de dados

Usa H2 (arquivo) com `schema.sql` e `data.sql`. O arquivo fica em `./data/paokentin-db`.
