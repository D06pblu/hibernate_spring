package hibernate_spring.controllers;

import hibernate_spring.dao.PersonDAO;
import hibernate_spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()  // адрес пустой, потому что получаем список всех людей просто по /people
    public String index(Model model){
        // получим всех людей из DAO и передадим на представление
        model.addAttribute("people", personDAO.index());
        return "people/index"; //указываем что возвращаем шаблон с таким вот адресом, в который будет набита полученная инфа
    }

    @GetMapping("/{id}") // в скобках {} указываем переменную id, которую будем считывать и далее в методе подставлять
    public String show(@PathVariable("id") long id, Model model){
        // получим одного человека по его id из DAO и передадим на представление
        model.addAttribute("person", personDAO.show(id));
        return "people/show"; //то же самое - возвращаем шаблон, наполненный данными
    }

    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("person", new Person());
        return "people/new";
    }

//    @PostMapping
//    public String create(@ModelAttribute("person") @Valid Person person,
//                         BindingResult bindingResult){
//        personValidator.validate(person, bindingResult); //валидатор значений в БД
//        if (bindingResult.hasErrors()){
//            return "people/new"; //если видим ошибку, то выдаем страницу создания с описанием ошибки
//        }
//        personDAO.save(person);
//        return "redirect:/people";
//    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id){
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

//    @PatchMapping("/{id}")
//    public String update(@ModelAttribute("person") @Valid Person person,
//            BindingResult bindingResult, @PathVariable("id") int id) {
//        personValidator.validate(person, bindingResult);
//        if (bindingResult.hasErrors()){
//            return "people/edit";
//        }
//        personDAO.update(id, person);
//        return "redirect: /people";
//    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id){
        personDAO.delete(id);
        return "redirect:/people";
    }
}
