package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.Optional;

@Repository
@Primary
@AllArgsConstructor
public class AccidentJdbcTemplate implements AccidentRep {

    private AccidentsExtractor extractor;
    private final JdbcTemplate jdbc;
    private static final String GET_ALL_ACCIDENTS = "select accidents.id, accidents.name, accidents.text," +
            " accidents.address, accident_types.name as typeName, rules.name as  ruleName from accidents " +
            "join accident_types on accident_type_id = accident_types.id " +
            "join accidents_rules on accidents.id = accidents_rules.accident_id join rules on accidents_rules.rule_id = rules.id;";

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
        return jdbc.query(GET_ALL_ACCIDENTS, extractor);
    }
}