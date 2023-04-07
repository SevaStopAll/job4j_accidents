package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TypeHibernate implements TypeRep {
    private final SessionFactory sf;
    private static final String DELETE = "DELETE FROM AccidentType WHERE id = :fId";
    private static final String FIND_ALL = "FROM AccidentType";
    private static final String FIND_BY_ID = "FROM AccidentType WHERE id = :fId";
    private final CrudRepository crudRepository;

    @Override
    public Optional<AccidentType> create(AccidentType type) {
        crudRepository.run(session -> session.persist(type));
        return Optional.ofNullable(type);
    }

    @Override
    public boolean update(AccidentType type) {
        try (Session session = sf.openSession()) {
            session.merge(type);
            return true;
        }
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.booleanQuery(DELETE, Map.of("fId", id));
    }

    @Override
    public Collection<AccidentType> findAll() {
        return crudRepository.query(FIND_ALL, AccidentType.class);
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return crudRepository.optional(
                FIND_BY_ID, AccidentType.class,
                Map.of("fId", id)
        );
    }
}
