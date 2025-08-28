package com.paokentin.repository;

import com.paokentin.model.Pao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class PaoRepository {

    private final JdbcTemplate jdbc;

    public PaoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private static class PaoMapper implements RowMapper<Pao> {
        @Override
        public Pao mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Pao(
                    rs.getLong("id"),
                    rs.getString("descricao"),
                    rs.getInt("tempo_preparo_minutos"),
                    rs.getString("cor_hex")
            );
        }
    }

    public Pao save(Pao p) {
        jdbc.update("INSERT INTO pao(descricao, tempo_preparo_minutos, cor_hex) VALUES(?,?,?)",
                p.getDescricao(), p.getTempoPreparoMinutos(), p.getCorHex());
        Long id = jdbc.queryForObject("SELECT IDENTITY()", Long.class);
        p.setId(id);
        return p;
    }

    public List<Pao> findAll() {
        return jdbc.query("SELECT * FROM pao ORDER BY id", new PaoMapper());
        }

    public Optional<Pao> findById(Long id) {
        List<Pao> list = jdbc.query("SELECT * FROM pao WHERE id=?", new PaoMapper(), id);
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }
}
