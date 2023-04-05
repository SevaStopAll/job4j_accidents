package ru.job4j.accidents.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RulesExtractor implements ResultSetExtractor<Collection> {

    @Override
    public Collection<Rule> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Collection<Rule> data = new ArrayList<>();
        while (rs.next()) {
            Rule rule = new Rule();
            rule.setName(rs.getString("ruleName"));
        }
        return data;
    }
}
