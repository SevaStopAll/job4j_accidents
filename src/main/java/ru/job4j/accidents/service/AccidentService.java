package ru.job4j.accidents.service;

import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface AccidentService {

    Optional<Accident> create(Accident accident);

    boolean update(Accident accident);

    boolean delete(int id);

    Collection<Accident> findAll();

    Optional<Accident> findById(int id);

    List<AccidentType> getTypes();

    Accident setType(Accident accident, int id);

    AccidentType getType(int id);

}
