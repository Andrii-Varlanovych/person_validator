package ua.andrii.springcourse.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public interface PersonValidator extends Validator {
    @Override
    boolean supports(Class<?> clazz);

    @Override
    void validate(Object target, Errors errors);
}
