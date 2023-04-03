package ru.job4j.accidents.service;

import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.Optional;

public interface AccidentTypeService {

    Optional<AccidentType> create(AccidentType type);

    boolean update(AccidentType type);
    boolean delete(int id);

    Collection<AccidentType> findAll();

    Optional<AccidentType> findById(int id);
}
