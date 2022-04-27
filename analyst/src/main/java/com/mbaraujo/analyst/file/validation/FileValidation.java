package com.mbaraujo.analyst.file.validation;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
@Log4j2
public class FileValidation {

    public void isValid(MultipartFile file) throws IOException {
        if(file.getResource().contentLength() == 0 || file.isEmpty() || file.getSize() == 0){
            throw new IllegalStateException("O arquivo está vázio.");
        }
        if(file.getOriginalFilename().isEmpty() || file.getOriginalFilename() == null ||
                file.getResource().getFilename().length() < 5){
            log.info("filename: " + file.getResource().getFilename());
            log.info("original filename: " + file.getOriginalFilename());
            throw new IllegalStateException("O arquivo deve ser nomeado corretamente. (transacoes-ano-mes-dia)");
        }

        if (!file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1).contains("csv")) {
            throw new IllegalStateException("O arquivo deve possuir extensão .csv");
        }
    }
}
