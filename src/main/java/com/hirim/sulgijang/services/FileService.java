package com.hirim.sulgijang.services;

import com.hirim.sulgijang.common.utils.FileUtils;
import com.hirim.sulgijang.models.Image;
import com.hirim.sulgijang.repositories.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileService {

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public void uploadFile(MultipartFile file) throws IOException {
        FileUtils.uploadFileToBucket(file);
    }

    public void insertFile(Image image) {
        fileRepository.insertFile(image);
    }
}
