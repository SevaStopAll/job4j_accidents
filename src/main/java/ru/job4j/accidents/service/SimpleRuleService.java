package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class SimpleRuleService implements RuleService {
    private final RuleRepository repository;

    @Override
    public Optional<Rule> create(Rule rule) {
        return repository.create(rule);
    }

    @Override
    public Optional<Rule> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public Set<Rule> findByIds(String[] ids) {
        return repository.findByIds(ids);
    }

    @Override
    public List<Rule> findAll() {
        return repository.findAll();
    }
}
