package com.example.lab06.repository;

import com.example.lab06.pojo.Wizard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WizardService {
    @Autowired
    private static WizardRepository repository;
    public WizardService(WizardRepository repository) {
        this.repository = repository;
    }
    public static List<Wizard> retrieveWizards(){
        return repository.findAll();
    }

    public static Wizard retrieveWizardsByName(String name){
        return repository.findByName(name);
    }
    public static Wizard createWizard(Wizard wizard){
        return repository.save(wizard);
    }
    public static boolean deleteWizard(Wizard wizard){
        try {
            repository.delete(wizard);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static Wizard updateWizard(Wizard wizard){
        return repository.save(wizard);
    }
}
