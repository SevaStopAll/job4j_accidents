package ru.job4j.accidents.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;

public class RulesRepository extends CrudRepository<Rule, Integer> {
}
