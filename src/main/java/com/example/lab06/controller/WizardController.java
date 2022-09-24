package com.example.lab06.controller;

import com.example.lab06.pojo.Wizard;
import com.example.lab06.pojo.Wizards;
import com.example.lab06.repository.WizardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WizardController {
    @RequestMapping(value = "/wizards", method = RequestMethod.GET)
    public ResponseEntity<?> getWizards() {
        List<Wizard> wizards = WizardService.retrieveWizards();
        return ResponseEntity.ok(wizards);
    }
    @RequestMapping(value ="/addWizard/{name}/{sex}/{money}/{school}/{house}/{position}", method = RequestMethod.POST)
    public ResponseEntity<?> createWizard(@PathVariable("name") String name,
                                          @PathVariable("sex") String sex,
                                          @PathVariable("money") String money,
                                          @PathVariable("school") String school,
                                          @PathVariable("house") String house,
                                          @PathVariable("position") String position){
        String s = "";
        if(sex.equals("Male")){
            s = "m";
        }else {
            s = "f";
        }
        Wizard n = WizardService.createWizard(new Wizard(null, s, name, school, house, money, position));
        return ResponseEntity.ok(n);
    }

    @RequestMapping(value ="/deleteWizard/{name}", method = RequestMethod.POST)
    public boolean deleteBook(@PathVariable("name") String name){
        Wizard wizard = WizardService.retrieveWizardsByName(name);
        boolean status = WizardService.deleteWizard(wizard);
        return status;
    }
}
