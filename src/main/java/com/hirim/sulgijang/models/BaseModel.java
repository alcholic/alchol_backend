package com.hirim.sulgijang.models;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class BaseModel {
    private long createdBy;
    private long updatedBy;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
