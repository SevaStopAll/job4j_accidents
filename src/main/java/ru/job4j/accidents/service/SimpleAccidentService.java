package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleAccidentService {
    private final AccidentRepository accidentRepository;
    private final RulesRepository ruleService;

    public void create(Accident accident, String[] ruleIds) {
        List<Rule> rules = new ArrayList<>();
        for (String index : ruleIds) {
            rules.add(ruleService.findById(Integer.parseInt(index)).get());
        }
        accident.setRules(rules);
        accidentRepository.save(accident);
    }

    public List<Accident> getAll() {
        return accidentRepository.findAll();
    }

    public Optional<Accident> findById(int id) {
        return accidentRepository.findById(id);
    }

    public boolean update(Accident accident) {
        return !accidentRepository.save(accident).equals(Optional.empty());
    }
}
