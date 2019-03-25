package com.hirim.sulgijang.models;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class BaseModel {
    protected long createdBy;
    protected long updatedBy;
    protected Timestamp createdAt;
    protected Timestamp updatedAt;
}
