package com.hirim.sulgijang.models;

import lombok.Data;

@Data
public class Comment extends BaseModel{
    private long commentId;
    private long userId;
    private long diaryId;
    private String comment;
}
