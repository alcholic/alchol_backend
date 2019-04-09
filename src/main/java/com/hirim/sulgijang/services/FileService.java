package com.hirim.sulgijang.services;

import com.hirim.sulgijang.models.Photo;
import com.hirim.sulgijang.repositories.FileRepository;
import org.springframework.stereotype.Service;

@Service
public class FileService {

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public void insertFile(Photo photo) {
        fileRepository.insertFile(photo);
    }
}
