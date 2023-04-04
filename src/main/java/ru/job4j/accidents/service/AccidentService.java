package ru.job4j.accidents.service;

import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AccidentService {

    Optional<Accident> create(Accident accident, int typeId, String[] ruleIds);

    boolean update(Accident accident);

    boolean delete(int id);

    Collection<Accident> findAll();

    Optional<Accident> findById(int id);

    List<AccidentType> getTypes();

    Accident setType(Accident accident, int id);

    AccidentType getType(int id);

    List<Rule> getRules();

    Rule getRule(int id);

    Accident setRules(Accident accident, String[] ids);

}
