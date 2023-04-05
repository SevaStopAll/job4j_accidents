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
    private static final String GET_ALL_ACCIDENTS = "select accidents.id, accidents.name, accidents.text,"
            + " accidents.address, accident_types.name as typeName, rules.name as  ruleName from accidents "
            + "join accident_types on accident_type_id = accident_types.id "
            + "join accidents_rules on accidents.id = accidents_rules.accident_id join rules on accidents_rules.rule_id = rules.id;";
    private static final String INSERT_INTO_ACCIDENTS = "insert into accidents (name, text, address, accident_type_id) values(?, ?, ?, ?)";
    private static final String INSERT_INTO_ACCIDENTS_RULES = "insert into accidents_rules(accident_id, rule_id) "
            + "select accidents.id, ? from accidents "
            + "where accidents.name = ?";

    private static final String DELETE_ACCIDENT_RULES = "delete from accidents_rules where accident_id = ?";
    private static final String UPDATE_ACCIDENT = "update accidents set name = ?, " +
            "text = ?, address = ?, accident_type_id = ? where id  = ?";

    @Override
    public Optional<Accident> create(Accident accident) {
        jdbc.update(INSERT_INTO_ACCIDENTS,
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId());
            for (int index = 0; index < accident.getRules().size(); index++) {
        jdbc.update(INSERT_INTO_ACCIDENTS_RULES,
                accident.getRules().get(index).getId(),
                accident.getName());
            }
        return Optional.ofNullable(accident);
    }

    @Override
    public boolean update(Accident accident) {
        jdbc.update(DELETE_ACCIDENT_RULES, accident.getId());
        jdbc.update(
        UPDATE_ACCIDENT,
        accident.getName(),
        accident.getText(),
        accident.getAddress(),
        accident.getType().getId(),
        accident.getId());
        for (int index = 0; index < accident.getRules().size(); index++) {
            jdbc.update(INSERT_INTO_ACCIDENTS_RULES,
                    accident.getRules().get(index).getId(),
                    accident.getName());
        }
        return true;
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
        result = jdbc.queryForObject("select name, text, address from accidents where id = ?",
                (resultSet, rowNum) -> {
                    Accident accident = new Accident();
                    accident.setName(resultSet.getString("name"));
                    accident.setText(resultSet.getString("text"));
                    accident.setAddress(resultSet.getString("address"));
                    return accident;
                },
                id);
        return Optional.ofNullable(result);
    }

    @Override
    public Collection<Accident> findAll() {
        return jdbc.query(GET_ALL_ACCIDENTS, extractor);
    }
}