package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@AllArgsConstructor
public class AccidentMem implements AccidentRep {
    private Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final AtomicInteger nextId = new AtomicInteger(0);
    private static final Logger LOG = LoggerFactory.getLogger(AccidentMem.class);
    private final AccidentTypeRep types = new AccidentTypeMem();
    private final RuleRepository rules = new RuleMem();

    private AccidentMem() {
        Accident accident1 = new Accident();
        accident1.setName("Accident");
        accident1.setAddress("Marks, Lenina st.");
        accident1.setText("A drunk man sleeps in his car");
        accident1.setType(types.findById(1).get());
        accident1.setRules(rules.findAll());
        create(accident1);
    }

    @Override
    public Optional<Accident> create(Accident accident) {
        accident.setId(nextId.incrementAndGet());
        Accident result = accidents.putIfAbsent(accident.getId(), accident);
        return Optional.ofNullable(result);
    }

    @Override
    public boolean update(Accident accident) {
        return accidents
                .computeIfPresent(accident.getId(), (id, oldAccident) ->
                        new Accident(oldAccident.getId(), accident.getName(), accident.getText(),
                                accident.getAddress(), accident.getType(), accident.getRules())) != null;
    }

    @Override
    public boolean delete(int id) {
        return (accidents.remove(id) != null);
    }

    @Override
    public Collection<Accident> findAll() {
        return accidents.values();
    }

    @Override
    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(accidents.get(id));
    }

    @Override
    public List<AccidentType> getTypes() {
        return types.findAll().stream().toList();
    }

    @Override
    public AccidentType getType(int id) {
        return types.findById(id).get();
    }
}
