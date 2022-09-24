package com.example.lab06.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WizardService {
    @Autowired
    private WizardRepository repository;
}
