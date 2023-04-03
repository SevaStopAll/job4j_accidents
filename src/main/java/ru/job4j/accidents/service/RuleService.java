package ru.job4j.accidents.service;

import ru.job4j.accidents.model.Rule;

import java.util.Optional;
import java.util.Set;

public interface RuleService {
    Optional<Rule> create(Rule rule);

    Optional<Rule> findById(int id);

    Set<Rule> findByIds(String[] ids);

    Set<Rule> findAll();
}
