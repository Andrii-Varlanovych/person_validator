package ua.andrii.springcourse.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import ua.andrii.springcourse.dao.PeopleDao;

@Component
public class PersonValidatorHibernate implements PersonValidator {
    private final PeopleDao peopleDao;

    public PersonValidatorHibernate(PeopleDao peopleDao) {
        this.peopleDao = peopleDao;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        System.out.println("Hi");

    }
}
