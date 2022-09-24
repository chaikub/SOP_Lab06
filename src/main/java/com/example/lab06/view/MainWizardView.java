package com.example.lab06.view;

import com.example.lab06.pojo.Wizard;
import com.example.lab06.pojo.Wizards;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Route(value = "mainPage.it")
public class MainWizardView extends VerticalLayout {
    private TextField name;
    private RadioButtonGroup gender;
    private ComboBox position, school, house;
    private NumberField dollar;
    private Button previous, create, update, delete, next;
    private int index = -1;
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
        next = new Button(">>");

        HorizontalLayout hbtn = new HorizontalLayout();
        hbtn.add(previous, create, update, delete, next);
        add(name, gender, position, dollar, school, house, hbtn);

        next.addClickListener(event -> {
            List<Wizard> wizards = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/wizards")
                    .retrieve()
                    .bodyToMono(List.class)
                    .block();
            System.out.println(wizards.get(0).getClass().getName());
//            if(index >= wizards.size()-1){
//                index = 0;
//                System.out.println(index);
//                System.out.println(wizards.get(index));
//                System.out.println(wizards.get(index).getMoney());
//                name.setValue(wizards.get(0).getName()+"");
//                if(wizards.get(index).getSex().equals("m")){
//                    gender.setValue("Male");
//                }else {
//                    gender.setValue("Female");
//                }
//                position.setValue(wizards.get(index).getPosition());
//                dollar.setValue(Double.valueOf(wizards.get(index).getMoney()));
//                school.setValue(wizards.get(index).getSchool());
//                house.setValue(wizards.get(index).getSchool());
//            }else{
//                index +=1;
////                System.out.println(wizards.size());
//                System.out.println(index);
//                System.out.println(wizards.get(index));
//            }
        });

    }
}
