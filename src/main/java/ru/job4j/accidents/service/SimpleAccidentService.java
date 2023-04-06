package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleAccidentService implements AccidentService {
    private final AccidentJdbcTemplate accidentRep;
    private final TypeJdbcTemplate typeService;
    private final RuleJdbcTemplate ruleService;

    @Override
    public Optional<Accident> create(Accident accident, int typeId, String[] ruleIds) {
        accident.setType(typeService.findById(typeId).get());
        accident.getType().setId(typeId);
        accident.setRules(ruleService.findByIds(ruleIds));
        for (int i = 0; i < accident.getRules().size(); i++) {
            accident.getRules().get(i).setId(Integer.parseInt(ruleIds[i]));
        }
        return accidentRep.create(accident);
    }

    @Override
    public boolean update(Accident accident, int typeId, String[] ruleIds) {
        accident.setType(typeService.findById(typeId).get());
        accident.getType().setId(typeId);
        accident.setRules(ruleService.findByIds(ruleIds));
        for (int i = 0; i < accident.getRules().size(); i++) {
            accident.getRules().get(i).setId(Integer.parseInt(ruleIds[i]));
        }
        return accidentRep.update(accident);
    }

    @Override
    public boolean delete(int id) {
        return accidentRep.delete(id);
    }

    @Override
    public Collection<Accident> findAll() {
        Collection<Accident> accidents = accidentRep.findAll();
        for (Accident accident : accidents) {
            accident.setRules(ruleService.findByAccidentId(accident.getId()));
        }
        return accidents;
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
    public Accident setType(Accident accident, int id) {
        accident.setType(typeService.findById(id).get());
        return accident;
    }

    @Override
    public List<Rule> getRules() {
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
