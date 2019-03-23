package com.hirim.sulgijang.models;

import lombok.Data;

@Data
public class Photo extends BaseModel{
    private long photoId;
    private long diaryId;
}
