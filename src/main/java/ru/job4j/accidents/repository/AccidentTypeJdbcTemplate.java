package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.Optional;

@Repository
@Primary
@AllArgsConstructor
public class AccidentTypeJdbcTemplate implements AccidentTypeRep {
    private final JdbcTemplate jdbc;

    @Override
    public Optional<AccidentType> create(AccidentType type) {
        jdbc.update("insert into accident_types (name) values (?)",
                type.getName());
        return Optional.ofNullable(type);
    }

    @Override
    public boolean update(AccidentType type) {
        return jdbc.update(
                "insert into accident_types (name) values (?)",
                type.getName()) > 0;
    }

    @Override
    public boolean delete(int id) {
        return jdbc.update(
                "delete from accident_types where id = ?",
                Long.valueOf(id)) > 0;

    }

    @Override
    public Optional<AccidentType> findById(int id) {!!!!!!
        !!!!!!!!!!!!AccidentType result = jdbc.queryForObject("select name from accident_types where id = ?",!!!!!!!!!!!
                (resultSet, rowNum) -> {
                    AccidentType type = new AccidentType();
                    type.setName(resultSet.getString("name"));
                    return type;
                });
        return Optional.ofNullable(result);
    }

    @Override
    public Collection<AccidentType> findAll() {
        return jdbc.query("select id, name from accident_types",
                (rs, row) -> {
                    AccidentType type = new AccidentType();
                    type.setId(rs.getInt("id"));
                    type.setName(rs.getString("name"));
                    return type;
                });
    }
}
