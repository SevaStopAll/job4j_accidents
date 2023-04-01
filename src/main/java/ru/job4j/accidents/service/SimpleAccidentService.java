package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentRep;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleAccidentService implements AccidentService {
    private final AccidentRep accidentRep;

    @Override
    public Optional<Accident> create(Accident accident) {
        return accidentRep.create(accident);
    }

    @Override
    public boolean update(Accident accident) {
        return accidentRep.update(accident);
    }

    @Override
    public boolean delete(int id) {
        return accidentRep.delete(id);
    }

    @Override
    public Collection<Accident> findAll() {
        return accidentRep.findAll();
    }

    @Override
    public Optional<Accident> findById(int id) {
        return accidentRep.findById(id);
    }
}
