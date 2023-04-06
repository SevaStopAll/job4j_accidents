package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@AllArgsConstructor
public class TypeMem implements TypeRep {
    private Map<Integer, AccidentType> types = new ConcurrentHashMap<>();
    private final AtomicInteger nextId = new AtomicInteger(0);
    private static final Logger LOG = LoggerFactory.getLogger(AccidentMem.class);

    public TypeMem() {
        create(new AccidentType(1, "Две машины"));
        create(new AccidentType(2, "Машина и человек"));
        create(new AccidentType(3, "Машина и велосипед"));
    }

    @Override
    public Optional<AccidentType> create(AccidentType type) {
        type.setId(nextId.incrementAndGet());
        AccidentType result = types.putIfAbsent(type.getId(), type);
        return Optional.ofNullable(result);
    }

    @Override
    public boolean update(AccidentType type) {
        return types
                .computeIfPresent(type.getId(), (id, oldType) ->
                        new AccidentType(oldType.getId(), type.getName())) != null;
    }

    @Override
    public boolean delete(int id) {
        return (types.remove(id) != null);
    }

    @Override
    public Collection<AccidentType> findAll() {
        return types.values();
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return Optional.ofNullable(types.get(id));
    }

}
