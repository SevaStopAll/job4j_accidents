package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.Optional;

public interface TypeRep {

    Optional<AccidentType> create(AccidentType type);

    boolean update(AccidentType type);
    boolean delete(int id);

    Collection<AccidentType> findAll();

    Optional<AccidentType> findById(int id);

}
