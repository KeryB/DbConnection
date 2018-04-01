package ru.connection.db.controller;


import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import ru.connection.db.annotation.analizer.JfxAnnotationAnailizer;
import ru.connection.db.commons.ActorTableView;
import ru.connection.db.commons.CreatingComponents;
import ru.connection.db.commons.DayShootingTableView;
import ru.connection.db.entity.Actor;
import ru.connection.db.entity.DayShooting;
import ru.connection.db.entity.FilterActor;
import ru.connection.db.service.ActorService;
import ru.connection.db.service.DayShootingService;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

public class MainWindow extends CreatingComponents implements Initializable {

    @FXML
    private Tab filter;

    @FXML
    private Tab actors;

    @FXML
    private TableView<?> scenesTable;

    @FXML
    private TableView<DayShooting> dayShootingTable;

    @FXML
    private TableView<Actor> actorTable;

    @FXML
    private TableView<?> filterTable;

    @FXML
    private Tab dayShooting;

    @FXML
    private Tab scenes;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private AnchorPane anchorActor;

    @FXML
    private AnchorPane anchorScenes;

    @FXML
    private AnchorPane dayShootingAnchorPane;

    @FXML
    private AnchorPane filterPane;

    private JfxAnnotationAnailizer anailizer = JfxAnnotationAnailizer.getInstance();

    private ActorService actorService = ActorService.getInstance();

    private DayShootingService dayShootingService = DayShootingService.getInstance();

    public void initialize(URL location, ResourceBundle resources) {


        try {
            List<Method> newList = anailizer.sortMethods(Actor.class);

            List<Node> parse = anailizer.parse(newList);
            ComboBox comboBox = (ComboBox) parse.get(9);
            ObservableList<String> tableList = FXCollections.observableArrayList();
            for (Actor.Gender c : Actor.Gender.values()) {
                tableList.add(c.getGender());
            }
            comboBox.setItems(tableList);
            anchorActor.getChildren().addAll(parse);

            createSaveAndDeleteActionActor(parse);

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            List<Method> newList = anailizer.sortMethods(FilterActor.class);
            List<Node> parse = anailizer.parse(newList);
            anchorActor.getChildren().addAll(parse);

            createFindFilter(parse);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Node> parse = null;
        try {
            List<Method> newList = anailizer.sortMethods(DayShooting.class);
            parse = anailizer.parse(newList);
            dayShootingAnchorPane.getChildren().addAll(parse);
            createSaveAndDeleteActionDayShooting(parse);
        } catch (Exception e) {
            e.printStackTrace();
        }


        actors.setOnSelectionChanged(e -> {
            findActor();
        });

        List<Node> finalParse = parse;
        dayShooting.setOnSelectionChanged(e -> {
            findActorAndDayShooting(finalParse);
        });
    }

    private void findActorAndDayShooting(List<Node> parse) {
        ComboBox comboBox = (ComboBox) parse.get(9);
        List<Actor> all = actorService.findAll();
        ObservableList<String> tableList = FXCollections.observableArrayList();

        all.forEach(e -> {
            tableList.add(String.valueOf(e.getId()) + " " + e.getFirstName() + " " + e.getSecondName() + " " + e.getLastName());
        });

        comboBox.setItems(tableList);
        if (dayShootingTable.getItems().isEmpty()) {
            List<DayShooting> allDayShooting = dayShootingService.findAll();
            dayShootingTable = DayShootingTableView.createTableColumn(dayShootingTable);
            DayShootingTableView.addItems(allDayShooting, dayShootingTable);
        }
    }

    private void findActor() {

        if (actorTable.getItems().isEmpty()) {
            List<Actor> all = actorService.findAll();
            actorTable = ActorTableView.createTableColumn(actorTable);
            ActorTableView.addItems(all, actorTable);
        }
    }

    private void createSaveAndDeleteActionActor(List<Node> nodes) {
        JFXButton save = createComponent("Сохранить", 85, 198, 146, 36);
        anchorActor.getChildren().add(save);


        save.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {

            Actor actor = actorService.saveActors(nodes);
            if (actor != null) {
                List<Actor> all = actorService.findAll();
                ActorTableView.addItems(all, actorTable);
            }

        });

        JFXButton delete = createComponent("Удалить", 438, 202, 130, 36);
        anchorActor.getChildren().add(delete);

        delete.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            Actor selectedItem = actorTable.getSelectionModel().getSelectedItem();
            actorService.deleteActor(selectedItem);
            ActorTableView.deleteItem(selectedItem, actorTable);
        });
    }

    private void createSaveAndDeleteActionDayShooting(List<Node> nodes) {
        JFXButton save = createComponent("Сохранить", 85, 198, 146, 36);
        dayShootingAnchorPane.getChildren().add(save);


        save.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {

            DayShooting DayShooting = dayShootingService.saveDayShooting(nodes);
            if (DayShooting != null) {
                List<DayShooting> all = dayShootingService.findAll();
                DayShootingTableView.addItems(all, dayShootingTable);
            }

        });

        JFXButton delete = createComponent("Удалить", 438, 202, 130, 36);
        dayShootingAnchorPane.getChildren().add(delete);

        delete.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            DayShooting selectedItem = dayShootingTable.getSelectionModel().getSelectedItem();
            dayShootingService.deleteDayShooting(selectedItem);
            DayShootingTableView.deleteItem(selectedItem, dayShootingTable);
        });
    }

    private void createFindFilter(List<Node> parse) {

        JFXButton find = createComponent("Найти", 600, 190, 90, 36);

        find.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
            List<Actor> filter = actorService.findFilter(parse);
            if(filter != null){
                ActorTableView.addItems(filter,actorTable);
            }
        });

        anchorActor.getChildren().add(find);

        JFXButton clearFilter = createComponent("Очистить фильтр", 730, 190, 90, 36);

        clearFilter.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
            List<Actor> all = actorService.findAll();
            ActorTableView.addItems(all,actorTable);
        });
        anchorActor.getChildren().add(clearFilter);
    }


}
