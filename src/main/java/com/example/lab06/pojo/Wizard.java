package com.example.lab06.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Wizard")
public class Wizard {
    @Id
    private String _id;
    private String name;
    private String sex;
    private String school;
    private String house;
    private String money;
    private String position;

    public Wizard() {
    }
    public Wizard(String _id, String name, String sex, String school, String house, String money, String position) {
        this._id = _id;
        this.name = name;
        this.sex = sex;
        this.school = school;
        this.house = house;
        this.money = money;
        this.position = position;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
