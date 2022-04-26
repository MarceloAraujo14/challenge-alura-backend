package com.mbaraujo.analyst.template;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping
public class TemplateController {

    @GetMapping(path = {"/", "index", "", "home"})
    public String getHome(){
        return "index";
    }

    @GetMapping(path = "/success")
    public String getSucessPage(){
        return "success-page";
    }
}
