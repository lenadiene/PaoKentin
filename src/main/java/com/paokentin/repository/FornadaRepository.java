package com.paokentin.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.paokentin.model.Fornada;

@Repository
public class FornadaRepository {

    private final JdbcTemplate jdbc;

    public FornadaRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private static class FornadaMapper implements RowMapper<Fornada> {
        @Override
        public Fornada mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Fornada(
                    rs.getLong("id"),
                    rs.getLong("pao_id"),
                    rs.getTimestamp("inicio").toLocalDateTime()
            );
        }
    }

    public Fornada save(Fornada f) {
    jdbc.update("INSERT INTO fornada(pao_id, inicio) VALUES(?, ?)", 
                f.getPaoId(), java.sql.Timestamp.valueOf(f.getInicio()));
    // Buscar o último ID inserido
    Long id = jdbc.queryForObject("SELECT MAX(id) FROM fornada", Long.class);
    f.setId(id);
    return f;
}


    public Optional<Fornada> findUltimaByPaoId(Long paoId) {
        List<Fornada> list = jdbc.query(
                "SELECT * FROM fornada WHERE pao_id=? ORDER BY inicio DESC LIMIT 1",
                new FornadaMapper(), paoId);
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }
}
