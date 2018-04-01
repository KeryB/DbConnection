package ru.connection.db.service;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import ru.connection.db.entity.Actor;
import ru.connection.db.entity.FilterActor;
import ru.connection.db.repository.ActorRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class ActorService {

    private static ActorService instance = new ActorService();

    private ActorRepository actorRepository = new ActorRepository();

    public static ActorService getInstance() {
        return instance;
    }

    public Actor saveActors(List<Node> nodes) {

        Actor actor = new Actor();
        nodes.forEach(event -> {
            if (event instanceof DatePicker) {
                JFXDatePicker jfxDatePicker = (JFXDatePicker) event;
                if (jfxDatePicker.getValue() != null) {
                    actor.setBirthday(Date.valueOf(jfxDatePicker.getValue()));
                }
            } else if (event instanceof ComboBox) {
                ComboBox comboBox = (ComboBox) event;
                Object selectedItem = comboBox.getSelectionModel().getSelectedItem();
                Actor.Gender gender = null;
                for (Actor.Gender c : Actor.Gender.values()) {
                    if (c.getGender().equals(selectedItem)) {
                        gender = c;
                        break;
                    }
                }
                if (gender != null) {
                    actor.setGender(gender.name());
                }
                System.out.println(gender);
            }
        });
        JFXTextField firstName = (JFXTextField) nodes.get(3);
        if (!firstName.getText().isEmpty()) {
            actor.setFirstName(firstName.getText());
        }

        JFXTextField secondName = (JFXTextField) nodes.get(5);

        if (!secondName.getText().isEmpty()) {
            actor.setSecondName(secondName.getText());
        }

        JFXTextField lastName = (JFXTextField) nodes.get(7);

        if (!lastName.getText().isEmpty()) {
            actor.setLastName(lastName.getText());
        }

        JFXTextField salary = (JFXTextField) nodes.get(11);

        if (!salary.getText().isEmpty()) {
            String text = salary.getText();
            actor.setSalary(Double.parseDouble(salary.getText()));
        }

        deleteData(nodes);

        return actorRepository.save(actor);
    }

    private void deleteData(List<Node> nodes) {
        nodes.forEach(event -> {
            if (event instanceof JFXTextField) {
                JFXTextField jfxTextField = (JFXTextField) event;
                jfxTextField.setText("");
            }
        });
    }

    public List<Actor> findAll() {
        return actorRepository.findAll();
    }

    public void deleteActor(Actor actor){
        if(actor != null){
            actorRepository.delete(actor.getId());
        }
    }

    public List<Actor> findFilter(List<Node> parse) {

        FilterActor filterActor = new FilterActor();

        int count = 0;

        JFXDatePicker birthDayFrom = (JFXDatePicker) parse.get(1);
        LocalDate datFrom = birthDayFrom.getValue();
        if(datFrom != null){
            filterActor.setBirthDayFrom(datFrom);
            count++;
        }

        JFXDatePicker birthDayTo = (JFXDatePicker) parse.get(3);
        LocalDate dateTo = birthDayTo.getValue();
        if(dateTo != null){
            filterActor.setBirthDayTo(dateTo);
            count++;
        }

        JFXTextField salaryFrom = (JFXTextField) parse.get(5);
        if(!salaryFrom.getText().isEmpty()){
            filterActor.setSalaryActorFrom(Double.parseDouble(salaryFrom.getText()));
            count++;
        }

        JFXTextField salaryTo = (JFXTextField) parse.get(7);
        if(!salaryTo.getText().isEmpty()){
            filterActor.setSalaryActorTo(Double.parseDouble(salaryTo.getText()));
            count++;
        }

        JFXTextField name = (JFXTextField) parse.get(9);
        if(!name.getText().isEmpty()){
            filterActor.setName(name.getText());
            count++;
        }


        if(count != 0){
            return actorRepository.find(filterActor);
        }

        return null;
    }
}
