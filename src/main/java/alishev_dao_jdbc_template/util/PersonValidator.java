package alishev_dao_jdbc_template.util;

import alishev_dao_jdbc_template.dao.PersonDAO;
import alishev_dao_jdbc_template.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PersonDAO personDAO;

    @Autowired //на самом деле не обязательно ставить анноташку, но так принято и всем понятней
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) { //тут прописываем для каких объектов работает этот валидатор
        return Person.class.equals(aClass); //собственно вот и вся реализация
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o; //даункастим аргумент о до нашего person
// тут нам надо реализовать метод, который принимает Person и проверяет нет ли такого email уже у дргой Person
// Для этого надо запилить метод show(email), которого у нас пока нет. Идем в ДАО и там делаем
        if (personDAO.show(person.getEmail()).isPresent()){
            errors.rejectValue("email", "333", "Such email already exist in system");
            //1й аргумент - по какому полю ищем, можно добавить и другие ошибки
            //2й - код ошибки, который будем выдавать
            //3й - текстовое сообщение, которое покажем юзерам
        }
    }
}
