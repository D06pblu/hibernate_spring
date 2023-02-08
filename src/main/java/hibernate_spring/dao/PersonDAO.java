package hibernate_spring.dao;

import hibernate_spring.models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class PersonDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional  //указывает что все что проихсодит внутри метода надо делать внутри транзакцииб,
    public List<Person> index() {   //т.е. открыть транзакцию, сделать дела, закрыть тарнзакцию
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT p from Person p", Person.class).getResultList();
    }

    public Person show(long id){
        return null;
    }

    public void save(Person person) { //в апдейте значения указываем просто через запятую после стринги

    }

    public void update(long id, Person updatePerson) {
    }

    public void delete(long id) {
    }
}
