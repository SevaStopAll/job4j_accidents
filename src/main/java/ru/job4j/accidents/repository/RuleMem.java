package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@AllArgsConstructor
public class RuleMem implements RuleRepository {
    private Map<Integer, Rule> rules = new ConcurrentHashMap<>();
    private final AtomicInteger nextId = new AtomicInteger(0);
    private static final Logger LOG = LoggerFactory.getLogger(AccidentMem.class);

    public RuleMem() {
        create(new Rule(1, "Опасное вождение"));
        create(new Rule(2, "Использование телефона"));
        create(new Rule(3, "Езда в нетрезвом виде"));
    }

    @Override
    public Optional<Rule> create(Rule rule) {
        rule.setId(nextId.incrementAndGet());
        Rule result = rules.putIfAbsent(rule.getId(), rule);
        return Optional.ofNullable(result);
    }

    @Override
    public Optional<Rule> findById(int id) {
        return Optional.ofNullable(rules.get(id));
    }

    @Override
    public Set<Rule> findByIds(String[] ids) {
        Set<Rule> result = new HashSet<>();
        for (String index : ids) {
            result.add(rules.get(Integer.parseInt(index)));
        }
        return result;
    }

    @Override
    public Set<Rule> findAll() {
        return new HashSet<>(rules.values());
    }
}
