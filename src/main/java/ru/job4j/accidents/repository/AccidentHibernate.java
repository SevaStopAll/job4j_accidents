package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentHibernate implements AccidentRep {
    private final CrudRepository crudRepository;
    private static final String FIND_ALL = "FROM Accident a JOIN FETCH a.type JOIN FETCH a.rules";
    private static final String FIND_BY_ID = "FROM Accident a JOIN FETCH a.type JOIN FETCH a.rules WHERE a.id = :fId";
    private static final String DELETE = "FROM Accident a JOIN FETCH a.type JOIN FETCH a.rules WHERE a.id = :fId";

    /**
     * Сохранить в базе.
     *
     * @param accident инцидент.
     * @return инцидент с id.
     */
    @Override
    public Optional<Accident> create(Accident accident) {
        crudRepository.run(session -> session.persist(accident));
        return Optional.ofNullable(accident);
    }

    /**
     * Обновить инцидент в базе.
     *
     * @param accident инцидент.
     * @return успешное обновление.
     */
    @Override
    public boolean update(Accident accident) {
        crudRepository.run(session -> session.merge(accident));
        return true;
    }

    /**
     * Лист всех инцидентов
     *
     * @return список объявлений.
     */

    @Override
    public Collection<Accident> findAll() {
        return crudRepository.query(FIND_ALL, Accident.class);
    }


    /**
     * Найти инцидент по ID
     *
     * @return найденный инцидент.
     */
    @Override
    public Optional<Accident> findById(int postId) {
        return crudRepository.optional(
                FIND_BY_ID, Accident.class,
                Map.of("fId", postId)
        );
    }

    /**
     * Удалить инцидент по ID
     *
     * @param accidentIt ID происшествия.
     * @return успещное удаление
     */
    @Override
    public boolean delete(int accidentIt) {
        crudRepository.run(
                DELETE, Map.of("fId", accidentIt));
        return true;
    }
}