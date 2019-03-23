package com.hirim.sulgijang.models;

import lombok.Data;

@Data
public class User extends BaseModel{
    private long userId;
    private String userName;
}
