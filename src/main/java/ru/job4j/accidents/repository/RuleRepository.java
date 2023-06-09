package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.Rule;

import java.util.List;
import java.util.Optional;

public interface RuleRepository {

    Optional<Rule> create(Rule rule);

    Optional<Rule> findById(int id);

    List<Rule> findByIds(String[] ids);

    List<Rule> findAll();
}
