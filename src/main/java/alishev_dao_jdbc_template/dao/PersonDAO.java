package alishev_dao_jdbc_template.dao;

import alishev_dao_jdbc_template.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate; //это фактически коннекшн к БД

    @Autowired //потербовалось создать этот конструктор, чтобы поле jdbcTemplate не ругалось
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        //вот таким query делается запрос. Ему нужно скормить сам запрос + RowMapper, который будет говорить
        //какая строка из запроса какому полю в нашем объекте соответствует. См. класс PersonMapper
        //Вместо PersonMapper можно подать стандартный BeanPropertyRowMapper если(!) все поля объекта совпадают с полями таблицы
//        return jdbcTemplate.query("SELECT * FROM Person", new PersonMapper());
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class)); //тут поставим заводской класс
    }


    public Person show(int id){
        // в dbcTemplate.query всегда используется PreparedStatement, так что ? можно использовать по умолчанию
        // вторым аргументом нужно подать массив значений, которые будут подставлены в prepStatement
        // третьим аргументом снова RowMapper. Ну а дальше просто вытаскиваем одно значение из списка, который получается из query
       return jdbcTemplate.query("SELECT * FROM Person WHERE id=?",
               new Object[]{id}, new PersonMapper()).stream() //а тут поставим самописный
               .findAny().orElse(null);
    }

    public Optional<Person> show(String email){ //перегруженный метод, нужен для работы PersonValidator проверяющего дубликаты email
        return jdbcTemplate.query("SELECT * FROM Person WHERE email=?", new Object[]{email},
                new PersonMapper()).stream().findAny(); //findAny как раз возвращает Optional, так что даже немного сократили код
    }

    public void save(Person person) { //в апдейте значения указываем просто через запятую после стринги
        jdbcTemplate.update("INSERT INTO Person VALUES (nextval('person_id_seq'), ?, ?, ?)",
                person.getName(), person.getAge(), person.getEmail());
    }

    public void update(int id, Person updatePerson) {
        jdbcTemplate.update("UPDATE Person SET name=?, age=?, email=? WHERE id=?",
                updatePerson.getName(), updatePerson.getAge(), updatePerson.getEmail(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }
}
