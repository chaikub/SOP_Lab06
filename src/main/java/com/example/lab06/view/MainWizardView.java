package com.example.lab06.view;

import com.example.lab06.pojo.Wizard;
import com.example.lab06.pojo.Wizards;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static com.vaadin.flow.component.notification.Notification.Position.BOTTOM_START;

@Route(value = "mainPage.it")
public class MainWizardView extends VerticalLayout {
    private TextField name;
    private RadioButtonGroup gender;
    private ComboBox position, school, house;
    private NumberField dollar;
    private Button previous, create, update, delete, next;
    private Notification nf;
    private int index = -1;
    private String oldName = "";
    public MainWizardView() {
        name = new TextField();
        name.setPlaceholder("Fullname");

        gender = new RadioButtonGroup<>();
        gender.setLabel("Gender: ");
        gender.setItems("Male", "Female");
        gender.setValue("Male");

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

        nf = new Notification();

        HorizontalLayout hbtn = new HorizontalLayout();
        hbtn.add(previous, create, update, delete, next);
        add(name, gender, position, dollar, school, house, hbtn);

        next.addClickListener(event -> {
            List<Wizard> out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/wizards")
                    .retrieve()
                    .bodyToMono(List.class)
                    .block();
            if(index == out.size()-1){
                index = 0;
            }else{
                index += 1;
            }
            ObjectMapper mapper = new ObjectMapper();
            Wizard wizard = mapper.convertValue(out.get(index), Wizard.class);
            oldName = wizard.getName();
            name.setValue(wizard.getName());
            if(wizard.getSex().equals("m")){
                gender.setValue("Male");
            }else {
                gender.setValue("Female");
            }
            position.setValue(wizard.getPosition());
            dollar.setValue(Double.valueOf(wizard.getMoney()));
            school.setValue(wizard.getSchool());
            house.setValue(wizard.getSchool());
        });

        previous.addClickListener(event -> {
            List<Wizard> out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/wizards")
                    .retrieve()
                    .bodyToMono(List.class)
                    .block();
            if(index <= 0){
                index = out.size()-1;
            }else{
                index -= 1;
            }
            ObjectMapper mapper = new ObjectMapper();
            Wizard wizard = mapper.convertValue(out.get(index), Wizard.class);
            oldName = wizard.getName();
            name.setValue(wizard.getName());
            if(wizard.getSex().equals("m")){
                gender.setValue("Male");
            }else {
                gender.setValue("Female");
            }
            position.setValue(wizard.getPosition());
            dollar.setValue(Double.valueOf(wizard.getMoney()));
            school.setValue(wizard.getSchool());
            house.setValue(wizard.getSchool());
        });

        create.addClickListener(event -> {
            String newName = name.getValue();
            String newGender = gender.getValue()+"";
            String newPosition = position.getValue()+"";
            String newDollars = dollar.getValue()+"";
            String newSchool = school.getValue()+"";
            String newHouse = house.getValue()+"";


            Wizard out = WebClient.create()
                    .post()
                    .uri("http://localhost:8080/addWizard/"+newName+"/"+newGender+"/"+newDollars+"/"+newSchool+"/"+newHouse+"/"+newPosition)
                    .retrieve()
                    .bodyToMono(Wizard.class)
                    .block();
            if(out != null){
                nf = Notification.show("Wizard has been created", 3000, BOTTOM_START);
            }
        });

        update.addClickListener(event -> {
            String newName = name.getValue();
            String newGender = gender.getValue()+"";
            String newPosition = position.getValue()+"";
            String newDollars = dollar.getValue()+"";
            String newSchool = school.getValue()+"";
            String newHouse = house.getValue()+"";
            boolean out = WebClient.create()
                    .post()
                    .uri("http://localhost:8080/updateWizard/"+oldName+"/"+newName+"/"+newGender+"/"+newDollars+"/"+newSchool+"/"+newHouse+"/"+newPosition)
                    .retrieve()
                    .bodyToMono(boolean.class)
                    .block();
            if(out){
                nf = Notification.show("Wizard has been updated", 3000, BOTTOM_START);
            }
        });

        delete.addClickListener(event -> {
            String newName = name.getValue();
            boolean out = WebClient.create()
                    .post()
                    .uri("http://localhost:8080/deleteWizard/"+newName)
                    .retrieve()
                    .bodyToMono(boolean.class)
                    .block();
            if(out){
                nf = Notification.show("Wizard has been updated", 3000, BOTTOM_START);
            }
        });

    }
}
