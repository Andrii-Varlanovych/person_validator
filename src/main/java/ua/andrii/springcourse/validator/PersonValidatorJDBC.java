package ua.andrii.springcourse.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import ua.andrii.springcourse.dao.PeopleDaoJDBC;
import ua.andrii.springcourse.model.Person;

@Component
public class PersonValidatorJDBC implements PersonValidator {
    private PeopleDaoJDBC peopleDaoJDBC;

    @Autowired
    public PersonValidatorJDBC(PeopleDaoJDBC peopleDaoJDBC) {
        this.peopleDaoJDBC = peopleDaoJDBC;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if (peopleDaoJDBC.getPersonByEmail(person.getEmail()).isPresent()) {
            errors.rejectValue("email", "", "Person with this email is already exists in DB");
        }
    }
}
