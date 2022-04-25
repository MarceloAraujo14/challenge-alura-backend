package com.mbaraujo.file.mapper;

import com.mbaraujo.file.model.FileModel;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileMapper {

    public FileModel toModel(MultipartFile file){
        FileModel model = new FileModel();

        model.setType(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1));
        model.setName(file.getOriginalFilename().replace("." + model.getType(), ""));
        model.setSize(file.getSize());
        return model;
    }
}
