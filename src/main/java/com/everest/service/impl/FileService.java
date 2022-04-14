package com.everest.service.impl;

import com.everest.entity.Files;
import com.everest.exception.NotFoundFileException;
import com.everest.repository.FilesRepository;
import com.everest.service.IFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Slf4j
@Service
public class FileService implements IFileService {

    @Autowired
    private FilesRepository repository;
    @Value("${com.everest.pathUnpack}")
    private String pathUnpack;

    @Override
    public List<Files> getAllFiles() {
        List<Files> filesList = new ArrayList<>();

        repository.findAll().forEach(files -> filesList.add(files));

        return filesList;
    }

    @Override
    public Files getFileByPath(String name) {
        return repository.findByFilePath(name);
    }

    @Override
    public Resource getFileByUrl(String url) throws IOException {
        Files files = repository.findByUrl(url);

        if (fileExists(files)) {
            Path path = getPathFromZipArch(files);
            ByteArrayResource resource = new ByteArrayResource(java.nio.file.Files.readAllBytes(path));
            return resource;
        }

        throw new NotFoundFileException();
    }

    private Path getPathFromZipArch(Files files){
        String name = "";
        ZipEntry entry;
        try(ZipInputStream zin = new ZipInputStream(new FileInputStream(files.getFilePath())))
        {
            while((entry=zin.getNextEntry())!=null){
                name = entry.getName();

                FileOutputStream fout = new FileOutputStream(pathUnpack + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        }
        catch(Exception ex){
            log.error(ex.getMessage());
        }

        return Path.of(pathUnpack+name);
    }

    private Boolean fileExists(Files files){
        File file = new File(files.getFilePath());
        if (file.exists()){
            return true;
        }else {
            files.setIsEmptyFile(true);
            repository.save(files);
            return false;
        }
    }
}
