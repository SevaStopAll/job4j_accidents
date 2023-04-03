package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeRep;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleAccidentTypeService implements AccidentTypeService {
    AccidentTypeRep typeRep;

    @Override
    public Optional<AccidentType> create(AccidentType type) {
        return typeRep.create(type);
    }

    @Override
    public boolean update(AccidentType type) {
        return typeRep.update(type);
    }

    @Override
    public boolean delete(int id) {
        return typeRep.delete(id);
    }

    @Override
    public Collection<AccidentType> findAll() {
        return typeRep.findAll();
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return typeRep.findById(id);
    }
}
