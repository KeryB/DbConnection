package ru.connection.db.commons;

import com.jfoenix.controls.JFXButton;

public class CreatingComponents {

    protected JFXButton createComponent(String text, double layoutX, double layoutY, double prefWidth, double prefHeight){

        JFXButton button= new JFXButton(text);
        button.setLayoutX(layoutX);
        button.setLayoutY(layoutY);
        button.setPrefWidth(prefWidth);
        button.setPrefHeight(prefHeight);
        return button;
    }

}
