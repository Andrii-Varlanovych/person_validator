package ua.andrii.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.andrii.springcourse.model.Person;
import ua.andrii.springcourse.util.PersonRowMapper;
import java.util.List;
import java.util.Optional;

@Component
@Primary
public class PeopleDaoJDBCTemplate implements PeopleDao {
    private JdbcTemplate jdbcTemplate;
    private PersonRowMapper personRowMapper;

    @Autowired
    public PeopleDaoJDBCTemplate(JdbcTemplate jdbcTemplate, PersonRowMapper personRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.personRowMapper = personRowMapper;
    }

    @Override
    public List<Person> getPeople() {
        String SQL = "SELECT * FROM Person";
        return jdbcTemplate.query(SQL, personRowMapper);
    }

    @Override
    public Optional<Person> getPersonById(int id) {
        String SQL = "SELECT * FROM person WHERE id = ?";
        List<Person> personList = jdbcTemplate.query(SQL, new Object[]{id}, personRowMapper);
        return Optional.of(personList
                .stream()
                .findFirst()
                .orElseThrow(()->new RuntimeException("Can't get person with id " + id + " from DB")));
    }

    @Override
    public void createPerson(Person person) {
        String SQL = "INSERT INTO person(name, age, email, address) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(SQL, person.getName(), person.getAge(), person.getEmail(), person.getAddress());
    }

    @Override
    public void updatePerson(int id, Person updatedPerson) {
        String SQL = "UPDATE person SET name = ?, age = ?, email = ?, address = ? WHERE id = ?";
        jdbcTemplate.update(SQL,
                updatedPerson.getName(),
                updatedPerson.getAge(),
                updatedPerson.getEmail(),
                updatedPerson.getAddress(),
                id);
    }

    @Override
    public void deletePerson(int id) {
        String SQL = "DELETE FROM person WHERE id = ?";
        jdbcTemplate.update(SQL, id);
    }

    public Optional<Person> getPersonByEmail(String email) {
        String SQL = "SELECT * FROM person WHERE email = ?";
        List<Person> personList = jdbcTemplate.query(SQL, new Object[]{email}, personRowMapper);
        return personList.stream().findFirst();
    }
}
