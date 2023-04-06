package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@AllArgsConstructor
public class AccidentMem implements AccidentRep {
    private Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final AtomicInteger nextId = new AtomicInteger(0);
    private static final Logger LOG = LoggerFactory.getLogger(AccidentMem.class);

    private AccidentMem() {
        Accident accident1 = new Accident();
        accident1.setName("Accident");
        accident1.setAddress("Marks, Lenina st.");
        accident1.setText("A drunk man sleeps in his car");
        accident1.setType(new AccidentType(1, "Две машины"));
        accident1.setRules(List.of(new Rule(1, "Статья. 1"),
                        new Rule(2, "Статья. 2"), new Rule(3, "Статья. 3")));
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

}
