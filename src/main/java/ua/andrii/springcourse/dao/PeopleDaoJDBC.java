package ua.andrii.springcourse.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ua.andrii.springcourse.model.Person;
import ua.andrii.springcourse.util.JDBCConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PeopleDaoJDBC implements PeopleDao {
    private Connection jdbcConnection = JDBCConnection.getJDBCConnection();

    @Override
    public List<Person> getPeople() {
        List<Person> personList = new ArrayList<>();

        try {
            Statement statement = jdbcConnection.createStatement();
            String SQL = "SELECT * FROM person";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                personList.add(getPerson(resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Can't create connection to DB", e);
        }
        return personList;
    }

    @Override
    public Optional<Person> getPersonById(int id) {
        try {
            PreparedStatement preparedStatement
                    = jdbcConnection.prepareStatement("SELECT * FROM person WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return Optional.of(getPerson(resultSet));

        } catch (SQLException e) {
            throw new RuntimeException("Can't create connection to DB", e);
        }
    }

    @Override
    public void createPerson(Person person) {
        try {
            PreparedStatement preparedStatement
                    = jdbcConnection.prepareStatement("INSERT INTO person(name, age, email) " +
                    "VALUES (?, ?, ?)");
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Can't create connection to DB", e);
        }
        /*try {
            Statement statement = jdblConnection.createStatement();
            String SQL = "INSERT INTO Person VALUES(" + 1 + ",'" + person.getName() +
                    "'," + person.getAge() + ",'" + person.getEmail() + "')";
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //insert into person values (5, 'Donald', 67, 'donald@ukr.net');*/

    }

    @Override
    public void updatePerson(int id, Person updatedPerson) {
        try {
            PreparedStatement preparedStatement
                    = jdbcConnection.prepareStatement
                    ("UPDATE person SET name = ?, age = ?, email = ? WHERE id = ?");
            preparedStatement.setString(1, updatedPerson.getName());
            preparedStatement.setInt(2, updatedPerson.getAge());
            preparedStatement.setString(3, updatedPerson.getEmail());
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Can't create connection to DB", e);
        }

    }

    @Override
    public void deletePerson(int id) {
        try {
            PreparedStatement preparedStatement
                    = jdbcConnection.prepareStatement("DELETE FROM person WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Person> getPersonByEmail(String email) {
        try {
            PreparedStatement preparedStatement
                    = jdbcConnection
                    .prepareStatement("SELECT * FROM person WHERE email = ?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return Optional.empty();
            }
            return Optional.of(getPerson(resultSet));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Person getPerson(ResultSet resultSet) throws SQLException {
        Person person = new Person();
        person.setId(resultSet.getInt("id"));
        person.setName(resultSet.getString("name"));
        person.setAge(resultSet.getInt("age"));
        person.setEmail(resultSet.getString("email"));
        return person;
    }
}
