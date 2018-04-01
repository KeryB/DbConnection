package ru.connection.db.commons;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ru.connection.db.entity.Actor;
import ru.connection.db.utils.TablesUtils;

import java.time.LocalDate;
import java.util.List;

public class ActorTableView  {

    public static TableView<Actor> createTableColumn(TableView<Actor> tableView) {

        TableColumn<Actor, Integer> id = new TableColumn<>(TablesUtils.ACTOR_ID);
        id.setCellValueFactory(new PropertyValueFactory<Actor, Integer>("id"));

        TableColumn<Actor, LocalDate> birthday = new TableColumn<>(TablesUtils.ACTOR_BIRTHDAY);
        birthday.setCellValueFactory(new PropertyValueFactory<Actor, LocalDate>("birthday"));

        TableColumn<Actor, String> firstName = new TableColumn<>(TablesUtils.ACTOR_FIRST_NAME);
        firstName.setCellValueFactory(new PropertyValueFactory<Actor, String>("firstName"));

        TableColumn<Actor, String> secondName = new TableColumn<>(TablesUtils.ACTOR_SECOND_NAME);
        secondName.setCellValueFactory(new PropertyValueFactory<Actor, String>("secondName"));

        TableColumn<Actor, String> lastName = new TableColumn<>(TablesUtils.ACTOR_LAST_NAME);
        lastName.setCellValueFactory(new PropertyValueFactory<Actor, String>("lastName"));

        TableColumn<Actor, String> gender = new TableColumn<>(TablesUtils.ACTOR_GENDER);
        gender.setCellValueFactory(new PropertyValueFactory<Actor, String>("gender"));

        TableColumn<Actor, Integer> salary = new TableColumn<>(TablesUtils.ACTOR_SALARY);
        salary.setCellValueFactory(new PropertyValueFactory<Actor, Integer>("salary"));

        tableView.getColumns().addAll(id, birthday, firstName, secondName, lastName, gender, salary);

        return tableView;
    }

    public static void addItems(List<Actor> actorList, TableView<Actor> tableView) {
        ObservableList<Actor> tableList = FXCollections.observableArrayList();
        tableList.addAll(actorList);
        tableView.setItems(tableList);
    }

    public static void addItem(Actor actor, TableView<Actor> tableView){
        ObservableList<Actor> tableList = tableView.getItems();
        tableList.add(actor);
        tableView.setItems(tableList);
    }

    public static void deleteItem(Actor selectedItem, TableView<Actor> tableView){
        ObservableList<Actor> items = tableView.getItems();
        items.remove(selectedItem);
        tableView.setItems(items);
    }
}
