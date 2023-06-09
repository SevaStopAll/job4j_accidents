package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.util.*;

@Repository
@Primary
@AllArgsConstructor
public class RuleJdbcTemplate implements RuleRepository {
    private final JdbcTemplate jdbc;
    private final static String GET_BY_ACCIDENT_NAME = "select name from rules join accidents_rules on rules.id = accidents_rules.rule_id where accidents_rules.accident_id = ?";

    @Override
    public Optional<Rule> create(Rule rule) {
        jdbc.update("insert into rules (name) values (?)",
                rule.getName());
        return Optional.ofNullable(rule);
    }

    @Override
    public Optional<Rule> findById(int id) {
        Rule result;
        result = jdbc.queryForObject("select name from rules where id = ?",
                (resultSet, rowNum) -> {
                    Rule rule = new Rule();
                    rule.setName(resultSet.getString("name"));
                    return rule;
                },
                id);
        return Optional.ofNullable(result);
    }

    @Override
    public List<Rule> findByIds(String[] ids) {
        List<Rule> result = new ArrayList<>();
        for (String index : ids) {
            result.add(findById(Integer.parseInt(index)).get());
        }
        return result;
    }

    @Override
    public List<Rule> findAll() {
        return jdbc.query("select id, name from rules",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }

    public List<Rule> findByAccidentId(int id) {
        return jdbc.query(GET_BY_ACCIDENT_NAME,
        (resultSet, rowNum) -> {
            Rule rule = new Rule();
            rule.setName(resultSet.getString("name"));
            return rule;
        },
                id);

    }
}
