package com.hirim.sulgijang.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends BaseModel{
    private long userId;
    private String userName;
    private String snsType;
    private String snsId;
    private String email;
}
