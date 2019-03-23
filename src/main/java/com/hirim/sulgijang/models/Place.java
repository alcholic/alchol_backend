package com.hirim.sulgijang.models;

import lombok.Data;

@Data
public class Place extends BaseModel{
    private long placeId;
    private String placeName;
    private String address;
    private String tel;
}