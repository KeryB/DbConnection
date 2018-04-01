package ru.connection.db.entity;

import lombok.Setter;
import ru.connection.db.annotation.Builder;
import ru.connection.db.annotation.JfxBuilderImpl;
import ru.connection.db.annotation.SortedMethod;

import java.time.LocalDate;
import java.util.Date;

@Setter
public class DayShooting {

    private int id;

    private LocalDate dateShooting;

    private String resources;

    private int startTime;

    private int endTime;

    private int id_actor;

    private String location;


    public int getId() {
        return id;
    }

    @Builder(name = "Дата съемок", type = JfxBuilderImpl.Type.Label, layOutX = 28, layOutY = 50)
    @Builder(name = "Введите дату съемок", type = JfxBuilderImpl.Type.DatePicker, layOutX = 160, layOutY = 50)
    @SortedMethod(sortIndex = 0)
    public LocalDate getDateShooting() {
        return dateShooting;
    }

    @Builder(name = "Ресурс", type = JfxBuilderImpl.Type.Label, layOutX = 28, layOutY = 90)
    @Builder(name = "Введите ресурс", type = JfxBuilderImpl.Type.JFXTextField, layOutX = 160, layOutY = 90)
    @SortedMethod(sortIndex = 1)
    public String getResources() {
        return resources;
    }

    @Builder(name = "Время начала съемок", type = JfxBuilderImpl.Type.Label, layOutX = 28, layOutY = 130)
    @Builder(name = "Выбере время начала съемок", type = JfxBuilderImpl.Type.TimePicker, layOutX = 160, layOutY = 130)
    @SortedMethod(sortIndex = 2)
    public int getStartTime() {
        return startTime;
    }

    @Builder(name = "Время окончания съемок", type = JfxBuilderImpl.Type.Label, layOutX = 350, layOutY = 50)
    @Builder(name = "Введите время окончания съемок", type = JfxBuilderImpl.Type.TimePicker, layOutX = 500, layOutY = 50)
    @SortedMethod(sortIndex = 3)
    public int getEndTime() {
        return endTime;
    }

    @Builder(name = "Выберете актера", type = JfxBuilderImpl.Type.Label, layOutX = 350, layOutY = 90)
    @Builder(name = "Свяжите актера с днем съемок", type = JfxBuilderImpl.Type.ComboBox, layOutX = 500, layOutY = 90)
    @SortedMethod(sortIndex = 4)
    public int getId_actor() {
        return id_actor;
    }

    @Builder(name = "Локация съемок", type = JfxBuilderImpl.Type.Label, layOutX = 350, layOutY = 130)
    @Builder(name = "Введите локацию съемок", type = JfxBuilderImpl.Type.JFXTextField, layOutX = 500, layOutY = 130)
    @SortedMethod(sortIndex = 5)
    public String getLocation() {
        return location;
    }
}
