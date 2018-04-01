package ru.connection.db.annotation.analizer;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import ru.connection.db.annotation.Builder;
import ru.connection.db.annotation.JfxBuilder;
import ru.connection.db.annotation.JfxBuilderImpl;
import ru.connection.db.annotation.SortedMethod;
import ru.connection.db.entity.Actor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class JfxAnnotationAnailizer<T> {

    private static JfxAnnotationAnailizer instance = new JfxAnnotationAnailizer();

    public List<Node> parse(List<Method> methods) throws Exception {

        List<Node> nodeList = new ArrayList<>();


        for (Method method : methods) {

            if (findActualJfxAnnotation(method)) {
                method.getReturnType();
                JfxBuilder annotation = method.getAnnotation(JfxBuilder.class);
                Builder[] values = annotation.value();

                for (Builder builder : values) {
                    String name = builder.name();
                    JfxBuilderImpl.Type type = builder.type();
                    double x = builder.layOutX();
                    double y = builder.layOutY();
                    double width = builder.prefWidth();
                    Node node = findNodes(name, type, x, y, width);
                    nodeList.add(node);
                }
            }
        }
        return nodeList;
    }

    public List<Method> sortMethods(Class<?> clazz) {
        Method[] methods = clazz.getMethods();
        List<Method> list = new ArrayList<>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(SortedMethod.class)) {
                SortedMethod annotation = method.getAnnotation(SortedMethod.class);
                list.add(method);
            }
        }

        list.sort(Comparator.comparingInt(o -> o.getAnnotation(SortedMethod.class).sortIndex()));
        return list;
    }

    private Node findNodes(String name, JfxBuilderImpl.Type type, double x, double y, double prefWidth) {

        if (type == JfxBuilderImpl.Type.Label) {
            Label label = new Label(name);
            label.setLayoutX(x);
            label.setLayoutY(y);
            return label;
        } else if (type == JfxBuilderImpl.Type.JFXButton) {
            return new JFXButton(name);
        } else if (type == JfxBuilderImpl.Type.JFXTextField) {
            JFXTextField jfxTextField = new JFXTextField();
            jfxTextField.setLabelFloat(true);
            jfxTextField.setPromptText(name);
            jfxTextField.setPrefWidth(prefWidth);
            jfxTextField.setLayoutX(x);
            jfxTextField.setLayoutY(y);
            return jfxTextField;
        } else if (type == JfxBuilderImpl.Type.ComboBox) {
            ComboBox comboBox = new ComboBox();
            comboBox.setPromptText(name);
            comboBox.setLayoutX(x);
            comboBox.setLayoutY(y);
            return comboBox;
        } else if (type == JfxBuilderImpl.Type.DatePicker) {
            JFXDatePicker jfxDatePicker = new JFXDatePicker();
            jfxDatePicker.setPromptText(name);
            jfxDatePicker.setLayoutX(x);
            jfxDatePicker.setLayoutY(y);
            jfxDatePicker.setPrefWidth(prefWidth);
            return jfxDatePicker;
        } else if(type == JfxBuilderImpl.Type.TimePicker){
            JFXTimePicker jfxTimePicker = new JFXTimePicker();
            jfxTimePicker.setPromptText(name);
            jfxTimePicker.setLayoutX(x);
            jfxTimePicker.setLayoutY(y);
            return jfxTimePicker;
        }

        return null;
    }

    private boolean findActualJfxAnnotation(Method method) {
        return method.isAnnotationPresent(JfxBuilder.class);
    }


    public static JfxAnnotationAnailizer getInstance() {
        return instance;
    }
}
