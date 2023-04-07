package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.*;

import java.util.List;

@Service
@AllArgsConstructor
public class SimpleAccidentService {
    private final AccidentRepository accidentRepository;
    private final TypeJdbcTemplate typeService;
    private final RuleJdbcTemplate ruleService;


    public void create(Accident accident) {
        accidentRepository.save(accident);
    }

    public List<Accident> getAll() {
        return (List<Accident>) accidentRepository.findAll();
    }
   /* @Override
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
    }*/
}
