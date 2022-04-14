package com.everest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "files")
public class Files {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String  fileName;
    private String filePath;
    private String url;
    @Column(name = "is_empty")
    private Boolean isEmptyFile;

}
