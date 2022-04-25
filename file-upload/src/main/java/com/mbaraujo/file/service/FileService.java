package com.mbaraujo.file.service;

import com.mbaraujo.file.mapper.FileMapper;
import com.mbaraujo.file.model.FileModel;
import com.mbaraujo.file.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class FileService {

    private final FileRepository repository;
    private final FileMapper mapper;

    @Value("${storage.path}")
    private String path;


    public List<String> save(MultipartFile file) throws IOException {
        FileModel model = mapper.toModel(file);

        if (!model.getType().contains("csv")) {
            throw new IllegalStateException("O arquivo deve possuir extensão .csv");
        }

        var fullpath = path + model.getName() + "." + model.getType();
        String sucessMessage = "";
        try {
            Files.copy(file.getInputStream(), Path.of(fullpath), StandardCopyOption.REPLACE_EXISTING);
            sucessMessage = "Arquivo " + file.getOriginalFilename() + " salvo com sucesso";
        } catch (IOException e) {
            log.info(e.getMessage());
            throw new IOException("Não foi possível salvar o arquivo \"" + file.getOriginalFilename() + "\"");
        }

        repository.save(model);
        return List.of(model.toString(), sucessMessage);
    }
}
