package ru.connection.db.commons;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ru.connection.db.entity.Actor;
import ru.connection.db.entity.DayShooting;
import ru.connection.db.utils.TablesUtils;

import java.time.LocalDate;
import java.util.List;

public class DayShootingTableView {

    public static TableView<DayShooting> createTableColumn(TableView<DayShooting> tableView) {

        TableColumn<DayShooting, Integer> id = new TableColumn<>(TablesUtils.DAY_SHOOTING_ID);
        id.setCellValueFactory(new PropertyValueFactory<DayShooting, Integer>("id"));

        TableColumn<DayShooting, LocalDate> dateShooting = new TableColumn<>(TablesUtils.DAY_SHOOTING_DATE);
        dateShooting.setCellValueFactory(new PropertyValueFactory<DayShooting, LocalDate>("dateShooting"));

        TableColumn<DayShooting, String> resource = new TableColumn<>(TablesUtils.DAY_SHOOTING_RESOURCE);
        resource.setCellValueFactory(new PropertyValueFactory<DayShooting, String>("resources"));

        TableColumn<DayShooting, Integer> startTime = new TableColumn<>(TablesUtils.DAY_SHOOTING_START_TIME);
        startTime.setCellValueFactory(new PropertyValueFactory<DayShooting, Integer>("startTime"));

        TableColumn<DayShooting, Integer> endTime = new TableColumn<>(TablesUtils.DAY_SHOOTING_END_TIME);
        endTime.setCellValueFactory(new PropertyValueFactory<DayShooting, Integer>("endTime"));

        TableColumn<DayShooting, Integer> id_actor = new TableColumn<>(TablesUtils.DAY_SHOOTING_ID_ACTOR);
        id_actor.setCellValueFactory(new PropertyValueFactory<DayShooting, Integer>("id_actor"));

        TableColumn<DayShooting, String> location = new TableColumn<>(TablesUtils.DAY_SHOOTING_LOCATION);
        location.setCellValueFactory(new PropertyValueFactory<DayShooting, String>("location"));

        tableView.getColumns().addAll(id, dateShooting, resource, startTime, endTime, id_actor, location);

        return tableView;
    }

    public static void addItems(List<DayShooting> dayShootingList, TableView<DayShooting> tableView) {
        ObservableList<DayShooting> tableList = FXCollections.observableArrayList();
        tableList.addAll(dayShootingList);
        tableView.setItems(tableList);
    }

    public static void deleteItem(DayShooting selectedItem, TableView<DayShooting> tableView){
        ObservableList<DayShooting> items = tableView.getItems();
        items.remove(selectedItem);
        tableView.setItems(items);
    }
}
