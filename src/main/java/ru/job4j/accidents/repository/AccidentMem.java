package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@AllArgsConstructor
public class AccidentMem implements AccidentRep {
    Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private static final AccidentMem INSTANCE = new AccidentMem();
    private final AtomicInteger nextId = new AtomicInteger(0);
    static final Logger LOG = LoggerFactory.getLogger(AccidentMem.class);

    private AccidentMem() {
        Accident accident1 = new Accident();
        accident1.setName("Accident");
        accident1.setAddress("Marks, Lenina st.");
        accident1.setText("A drunk man sleeps in his car");
        create(accident1);
    }

    public static AccidentMem getInstance() {
        return INSTANCE;
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
                                accident.getAddress())) != null;
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
