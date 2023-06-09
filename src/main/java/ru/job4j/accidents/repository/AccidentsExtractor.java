package ru.job4j.accidents.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Component
public class AccidentsExtractor implements ResultSetExtractor<List> {

    @Override
    public List<Accident> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Accident> data = new ArrayList<>();
        while (rs.next()) {
            Accident accident = new Accident();
            accident.setId(rs.getInt("id"));
            accident.setName(rs.getString("name"));
            accident.setText(rs.getString("text"));
            accident.setAddress(rs.getString("address"));
            AccidentType type = new AccidentType();
            type.setName(rs.getString("typeName"));
            accident.setType(type);
/*            Rule rule = new Rule();
            rule.setName(rs.getString("ruleName"));
            accident.setRules(List.of(rule));*/
            data.add(accident);
        }
        return data;
    }
}

