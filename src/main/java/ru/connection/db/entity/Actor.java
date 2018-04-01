package ru.connection.db.entity;

import lombok.Setter;
import ru.connection.db.annotation.*;

import java.sql.Date;
import java.time.LocalDate;

@Setter
public class Actor {

    private int id;

    private Date birthday;

    private String firstName;

    private String secondName;

    private String lastName;

    private String gender;

    private double salary;

    public enum Gender {

        male("Мужской"), female("Женский");

        private String gender;

        Gender(String gender) {
            this.gender = gender;
        }

        public String getGender() {
            return gender;
        }

        public Gender getByName(String name){
            Gender gender = null;
            for (Gender c : Gender.values()) {
                if(c.getGender().equals(name)){
                    gender= c;
                    break;
                }
            }
            return gender;
        }
    }

    public int getId() {
        return id;
    }

    @Builder(name = "День рождения", type = JfxBuilderImpl.Type.Label, layOutX = 28, layOutY = 50)
    @Builder(name = "Введите день рождени", type = JfxBuilderImpl.Type.DatePicker, layOutX = 130, layOutY = 50)
    @SortedMethod(sortIndex = 0)
    public Date getBirthday() {
        return birthday;
    }

    @Builder(name = "Имя", type = JfxBuilderImpl.Type.Label, layOutX = 28, layOutY = 90)
    @Builder(name = "Введите имя", type = JfxBuilderImpl.Type.JFXTextField, layOutX = 130, layOutY = 90)
    @SortedMethod(sortIndex = 1)
    public String getFirstName() {
        return firstName;
    }

    @Builder(name = "Фамилия", type = JfxBuilderImpl.Type.Label, layOutX = 28, layOutY = 130)
    @Builder(name = "Введите фамилию", type = JfxBuilderImpl.Type.JFXTextField, layOutX = 130, layOutY = 130)
    @SortedMethod(sortIndex = 2)
    public String getSecondName() {
        return secondName;
    }

    @Builder(name = "Отчество", type = JfxBuilderImpl.Type.Label, layOutX = 300, layOutY = 50)
    @Builder(name = "Введите отчество", type = JfxBuilderImpl.Type.JFXTextField, layOutX = 410, layOutY = 50)
    @SortedMethod(sortIndex = 3)
    public String getLastName() {
        return lastName;
    }

    @Builder(name = "Выберете род", type = JfxBuilderImpl.Type.Label, layOutX = 300, layOutY = 90)
    @Builder(name = "Выберете род", type = JfxBuilderImpl.Type.ComboBox, layOutX = 410, layOutY = 90)
    @SortedMethod(sortIndex = 4)
    public String getGender() {
        return gender;
    }

    @Builder(name = "Зарплата актера", type = JfxBuilderImpl.Type.Label, layOutX = 300, layOutY = 130)
    @Builder(name = "Введите зарплату актера", type = JfxBuilderImpl.Type.JFXTextField, layOutX = 410, layOutY = 130)
    @SortedMethod(sortIndex = 5)
    public double getSalary() {
        return salary;
    }

}
