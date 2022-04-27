package com.mbaraujo.analyst.file.service;

import com.mbaraujo.analyst.file.mapper.FileMapper;
import com.mbaraujo.analyst.file.enitty.FileModel;
import com.mbaraujo.analyst.file.repository.FileRepository;
import com.mbaraujo.analyst.file.validation.FileValidation;
import com.mbaraujo.analyst.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
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
    private final TransactionService transactionService;
    @Autowired
    private FileValidation validation;

    @Value("${storage.path}")
    private String path;


    @SneakyThrows
    public List<String> save(MultipartFile file) {
        //if
        validation.isValid(file);

        FileModel model = mapper.toModel(file);
        var fullpath = path + model.getName() + "." + model.getType();
        var sucessMessage = "";

        try {
            processTransactions(file);

            Files.copy(file.getInputStream(), Path.of(fullpath), StandardCopyOption.REPLACE_EXISTING);

            sucessMessage = "Arquivo " + file.getOriginalFilename() + " salvo com sucesso";

        } catch (Exception e) {

            throw new IOException("Não foi possível salvar o arquivo \"" + file.getOriginalFilename() + "\" \n" +
                    "erro: " + e.getMessage());
        }

        repository.save(model);
        return List.of(model.toString(), sucessMessage);

        }

        public void processTransactions(MultipartFile file){
            try {
                transactionService.save(file.getInputStream());
            }catch (Exception e){
                throw new IllegalStateException(e.getMessage());
            }
        }

}
