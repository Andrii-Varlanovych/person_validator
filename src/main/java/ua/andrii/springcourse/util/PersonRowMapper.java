package ua.andrii.springcourse.util;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.andrii.springcourse.model.Person;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PersonRowMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();
        person.setId(rs.getInt("id"));
        person.setName(rs.getString("name"));
        person.setAge(rs.getInt("age"));
        person.setEmail(rs.getString("email"));
        person.setAddress(rs.getString("address"));
        return person;
    }
}
