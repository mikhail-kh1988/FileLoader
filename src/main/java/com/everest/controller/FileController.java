package com.everest.controller;

import com.everest.entity.Files;
import com.everest.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController()
public class FileController {

    @Autowired
    private IFileService service;

    @GetMapping("")
    public ResponseEntity<List<Files>> getAllFiles(){
        return ResponseEntity.ok(service.getAllFiles());
    }


    @GetMapping("/{url}/load")
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<Resource> getFile(@PathVariable String url) throws IOException {

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(service.getFileByUrl(url));
    }


}
