package com.mbaraujo.analyst.file.enitty;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
public class FileModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "file_id")
    @SequenceGenerator(name = "file_id", sequenceName = "file_id", allocationSize = 1)
    private Long id;
    private String name;
    private String type;
    private Long size;

    public FileModel(String name, String type, Long size) {
        this.name = name;
        this.type = type;
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FileModel fileModel = (FileModel) o;
        return id != null && Objects.equals(id, fileModel.id);
    }


}
