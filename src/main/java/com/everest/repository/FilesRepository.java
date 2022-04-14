package com.everest.repository;

import com.everest.entity.Files;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilesRepository extends CrudRepository<Files, Long> {

    Files findByFilePath(String name);
    Files findByUrl(String url);
}
