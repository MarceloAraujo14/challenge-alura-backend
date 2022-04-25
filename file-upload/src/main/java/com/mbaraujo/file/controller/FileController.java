package com.mbaraujo.file.controller;

import com.mbaraujo.file.service.FileService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file")
@Log4j2
@AllArgsConstructor
public class FileController {

    private final FileService service;


    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file")MultipartFile file, Model model){
        try{
            service.save(file);
            model.addAttribute("sucess", service.save(file).get(1));
            return "form-upload";
        }catch (Exception e){
            log.info(e.getMessage());
            model.addAttribute("erro", e.getMessage());
            return "form-upload";
        }

    }
}
