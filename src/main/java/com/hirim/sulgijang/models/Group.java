package com.hirim.sulgijang.models;

import lombok.Data;

import java.util.List;

@Data
public class Group extends BaseModel{
    private long groupId;
    private String groupName;
    private List<User> userList;
}
