package com.hirim.sulgijang.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Photo extends BaseModel{
    private long fileId;
    private long diaryId;
    private String fileName;
    private String fileUri;

    public Photo(long diaryId, String fileName, String fileUri) {
        this.diaryId = diaryId;
        this.fileName = fileName;
        this.fileUri = fileUri;
    }
}