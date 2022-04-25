package com.mbaraujo.form.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/form")
public class FormController {

    @GetMapping
    public String formUpload(){
        return "form-upload";
    }

}
