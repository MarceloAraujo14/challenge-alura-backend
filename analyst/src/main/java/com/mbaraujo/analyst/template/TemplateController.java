package com.mbaraujo.analyst.template;

import com.mbaraujo.analyst.transaction.entity.TransactionsRegister;
import com.mbaraujo.analyst.transaction.service.TransactionRegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping
public class TemplateController {

    private final TransactionRegisterService service;

    @GetMapping(path = {"/", "index", "", "home"})
    public String getHome(Model model){
        List<TransactionsRegister> transactionsRegisters = service.findAll();
        model.addAttribute("register", transactionsRegisters);
        return "index";
    }

    @GetMapping(path = "/success")
    public String getSucessPage(Model model){
        List<TransactionsRegister> transactionsRegisters = service.findAll();
        model.addAttribute("register", transactionsRegisters);
        return "success-page";
    }
}
