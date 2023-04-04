package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.AccidentRep;
import ru.job4j.accidents.repository.AccidentTypeRep;
import ru.job4j.accidents.repository.RuleRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class SimpleAccidentService implements AccidentService {
    private final AccidentRep accidentRep;
    private final AccidentTypeRep typeService;
    private final RuleRepository ruleService;

    private final RuleService ruleService;

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

    @Override
    public List<AccidentType> getTypes() {
        return typeService.findAll().stream().toList();
    }

    @Override
    public AccidentType getType(int id) {
        return typeService.findById(id).get();
    }

    @Override
    public Set<Rule> getRules() {
        return ruleService.findAll();
    }

    @Override
    public Rule getRule(int id) {
        return ruleService.findById(id).get();
    }

    @Override
    public Accident setRules(Accident accident, String[] ids) {
        accident.setRules(ruleService.findByIds(ids));
        return accident;
    }

    @Override
    public Accident setType(Accident accident, int id) {
        accident.setType(typeService.findById(id).get());
        return accident;
    }

    @Override
    public Set<Rule> getRules() {
        return ruleService.findAll();
    }

    @Override
    public Rule getRule(int id) {
        return ruleService.findById(id).get();
    }

    @Override
    public Accident setRules(Accident accident, String[] ids) {
        accident.setRules(ruleService.findByIds(ids));
        return accident;
    }

}
