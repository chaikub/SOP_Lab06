package com.example.lab06.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route(value = "mainPage.it")
public class MainWizardView extends VerticalLayout {
    private TextField name;
    private RadioButtonGroup gender;
    private ComboBox position, school, house;
    private NumberField dollar;
    private Button previous, create, update, delete, next;

    public MainWizardView() {
        name = new TextField();
        name.setPlaceholder("Fullname");

        gender = new RadioButtonGroup<>();
        gender.setLabel("Gender: ");
        gender.setItems("Male", "Female");

        position = new ComboBox<>();
        position.setItems("Student", "Teacher");
        position.setPlaceholder("Position");

        school = new ComboBox<>();
        school.setItems("Hogwarts", "Beauxbatons", "Durmstrang");
        school.setPlaceholder("School");

        house = new ComboBox<>();
        house.setItems("Gryffindor", "Ravenclaw", "Hufflepuff", "Slyther");
        house.setPlaceholder("House");

        dollar = new NumberField();
        dollar.setPlaceholder("Dollars");
        dollar.setPrefixComponent(new Span("$"));

        previous = new Button("<<");
        create = new Button("Create");
        update = new Button("Update");
        delete = new Button("Delete");
        next = new Button("Next");

        HorizontalLayout hbtn = new HorizontalLayout();
        hbtn.add(previous, create, update, delete, next);
        add(name, gender, position, dollar, school, house, hbtn);
    }
}
