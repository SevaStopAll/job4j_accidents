package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentJdbcTemplate implements AccidentRep {
    private final JdbcTemplate jdbc;

    @Override
    public Optional<Accident> create(Accident accident) {
        jdbc.update("insert into accidents (name) values (?)",
                accident.getName());
        return Optional.ofNullable(accident);
    }

    @Override
    public boolean update(Accident accident) {
        return jdbc.update(
                "insert into accidents (name) values (?)",
                accident.getName()) > 0;
    }

    @Override
    public boolean delete(int id) {
        return jdbc.update(
                        "delete from accidents where id = ?",
                Long.valueOf(id)) > 0;

    }

    @Override
    public Optional<Accident> findById(int id) {
        Accident result;
        result = jdbc.queryForObject("select name from accidents where id = ?",
                (resultSet, rowNum) -> {
                    Accident accident = new Accident();
                    accident.setName(resultSet.getString("name"));
                    return accident;
                });
        return Optional.ofNullable(result);
    }



    @Override
    public Collection<Accident> findAll() {
        return jdbc.query("select id, name from accidents",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    return accident;
                });
    }

}