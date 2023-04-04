package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.Optional;

public class AccidentTypeJdbcTemplate implements AccidentTypeRep {
    @Override
    public Optional<AccidentType> create(AccidentType type) {
        return Optional.empty();
    }

    @Override
    public boolean update(AccidentType type) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Collection<AccidentType> findAll() {
        return null;
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return Optional.empty();
    }
}
