package ru.connection.db.entity;


import lombok.Setter;
import ru.connection.db.annotation.Builder;
import ru.connection.db.annotation.JfxBuilderImpl;
import ru.connection.db.annotation.SortedMethod;
import ru.connection.db.entity.abstr.Filter;

import java.time.LocalDate;

@Setter
public class FilterActor extends Filter{

    private LocalDate birthDayFrom;

    private LocalDate birthDayTo;

    private Double salaryActorFrom;

    private Double salaryActorTo;

    private String name;

    @Builder(name = "До даты рождения", type = JfxBuilderImpl.Type.Label, layOutX = 575, layOutY = 55)
    @Builder(name = "Дата рождения", type = JfxBuilderImpl.Type.DatePicker, layOutX = 710, layOutY = 50)
    @SortedMethod(sortIndex = 0)
    public LocalDate getBirthDay() {
        return birthDayFrom;
    }

    @Builder(name = "После даты рождения", type = JfxBuilderImpl.Type.Label, layOutX = 575, layOutY = 85)
    @Builder(name = "Дата рождения", type = JfxBuilderImpl.Type.DatePicker, layOutX = 710, layOutY = 80)
    @SortedMethod(sortIndex = 1)
    public LocalDate getBirthDayTo() {
        return birthDayTo;
    }

    @Builder(name = "Зарплата с", type = JfxBuilderImpl.Type.Label, layOutX = 575, layOutY = 120)
    @Builder(name = "", type = JfxBuilderImpl.Type.JFXTextField, layOutX = 650, layOutY = 115, prefWidth = 50)
    @SortedMethod(sortIndex = 2)
    public Double getSalaryActorFrom() {
        return salaryActorFrom;
    }
    @Builder(name = "До", type = JfxBuilderImpl.Type.Label, layOutX = 720, layOutY = 120)
    @Builder(name = "", type = JfxBuilderImpl.Type.JFXTextField, layOutX = 750, layOutY = 115, prefWidth = 50)
    @SortedMethod(sortIndex = 3)
    public Double getSalaryActorTo() {
        return salaryActorTo;
    }

    @Builder(name = "Введите имя", type = JfxBuilderImpl.Type.Label, layOutX = 575, layOutY = 160)
    @Builder(name = "Имя", type = JfxBuilderImpl.Type.JFXTextField, layOutX = 710, layOutY = 150, prefWidth = 120)
    @SortedMethod(sortIndex = 4)
    public String getName() {
        return name;
    }

}
