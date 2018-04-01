package ru.connection.db.service;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import ru.connection.db.entity.DayShooting;
import ru.connection.db.repository.DayShootingRepository;

import java.time.LocalTime;
import java.util.List;

public class DayShootingService {

    private static DayShootingService instance = new DayShootingService();

    private DayShootingRepository dayShootingRepository = new DayShootingRepository();

    public static DayShootingService getInstance() {
        return instance;
    }

    public List<DayShooting> findAll() {
        return dayShootingRepository.findAll();
    }

    public DayShooting saveDayShooting(List<Node> nodes) {

        DayShooting dayShooting = new DayShooting();
        nodes.forEach(event -> {
            if (event instanceof DatePicker) {
                JFXDatePicker jfxDatePicker = (JFXDatePicker) event;
                if (jfxDatePicker.getValue() != null) {
                    dayShooting.setDateShooting(jfxDatePicker.getValue());
                    System.out.println(jfxDatePicker.getValue());
                }
            } else if (event instanceof ComboBox) {
                ComboBox comboBox = (ComboBox) event;
                String selectedItem = (String) comboBox.getSelectionModel().getSelectedItem();
                String[] split = selectedItem.split(" ");
                int idActor = Integer.parseInt(split[0]);
                System.out.println(split[0]);
                dayShooting.setId_actor(idActor);
            }
        });

        JFXTextField resources = (JFXTextField) nodes.get(3);
        if (!resources.getText().isEmpty()) {
            dayShooting.setResources(resources.getText());
        }

        JFXTimePicker startTime = (JFXTimePicker) nodes.get(5);
        if (startTime.getValue() != null) {
            LocalTime value = startTime.getValue();
            int seconds = value.getHour() * 3600 + value.getMinute() * 60 + value.getSecond();
            dayShooting.setStartTime(seconds);
        }

        JFXTimePicker endTime = (JFXTimePicker) nodes.get(7);
        if (endTime.getValue() != null) {
            LocalTime value = endTime.getValue();
            int seconds = value.getHour() * 3600 + value.getMinute() * 60 + value.getSecond();
            dayShooting.setEndTime(seconds);
        }

        JFXTextField location = (JFXTextField) nodes.get(11);
        if (!location.getText().isEmpty()) {
            dayShooting.setLocation(location.getText());
        }
        deleteData(nodes);
        return dayShootingRepository.save(dayShooting);
    }

    private void deleteData(List<Node> nodes) {
        nodes.forEach(event -> {
            if (event instanceof JFXTextField) {
                JFXTextField jfxTextField = (JFXTextField) event;
                jfxTextField.setText("");
            }
        });
    }

    public void deleteDayShooting(DayShooting selectedItem) {
        if(selectedItem != null){
            dayShootingRepository.delete(selectedItem.getId());
        }
    }
}
