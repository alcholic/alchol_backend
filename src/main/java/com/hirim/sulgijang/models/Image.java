package com.hirim.sulgijang.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Image extends BaseModel{
    private long fileId;
    private long diaryId;
    private String fileName;
    private String fileUri;

    public Image(String fileName, String fileUri) {
        this.fileName = fileName;
        this.fileUri = fileUri;
    }
}