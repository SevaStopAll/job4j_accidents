package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.Rule;

import java.util.Optional;
import java.util.Set;

public class RuleJdbcTemplate implements RuleRepository {
    @Override
    public Optional<Rule> create(Rule rule) {
        return Optional.empty();
    }

    @Override
    public Optional<Rule> findById(int id) {
        return Optional.empty();
    }

    @Override
    public Set<Rule> findByIds(String[] ids) {
        return null;
    }

    @Override
    public Set<Rule> findAll() {
        return null;
    }
}
