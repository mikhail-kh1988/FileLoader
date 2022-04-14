package com.everest.service;

import com.everest.entity.Files;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

public interface IFileService {

    List<Files> getAllFiles();
    Files getFileByPath(String name);
    Resource getFileByUrl(String url) throws IOException;
}
