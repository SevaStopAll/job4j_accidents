package ru.job4j.accidents.repository;

import org.springframework.jdbc.core.RowMapper;
import ru.job4j.accidents.model.AccidentType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccidentTypeMapper implements RowMapper<AccidentType> {
        @Override
        public AccidentType mapRow(ResultSet rs, int id) throws SQLException {
            AccidentType type = new AccidentType();
            type.setId(rs.getInt("id"));
            type.setName(rs.getString("name"));
            return type;
        }
}
