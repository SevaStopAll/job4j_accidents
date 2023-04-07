package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class RuleHibernate implements RuleRepository {
    private final SessionFactory sf;
    private final CrudRepository crudRepository;
    private static final String DELETE = "DELETE FROM Rule WHERE id = :fId";
    private static final String FIND_ALL = "FROM Rule";
    private static final String FIND_BY_ID = "FROM Rule WHERE id = :fId";

    @Override
    public Optional<Rule> create(Rule rule) {
        crudRepository.run(session -> session.persist(rule));
        return Optional.ofNullable(rule);
    }

    @Override
    public Optional<Rule> findById(int id) {
        return crudRepository.optional(
                FIND_BY_ID, Rule.class,
                Map.of("fId", id)
        );
    }

    @Override
    public List<Rule> findByIds(String[] ids) {
        return crudRepository.query("from Rule where id IN (:fValues)", Rule.class, Map.of("fValues", ids));
    }

    @Override
    public List<Rule> findAll() {
        return crudRepository.query(FIND_ALL, Rule.class);
    }
}
