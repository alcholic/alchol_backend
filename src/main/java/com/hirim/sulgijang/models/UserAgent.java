package com.hirim.sulgijang.models;

import lombok.Data;

@Data
public class UserAgent {
    private String deviceId = "";
    private String deviceType = "";
    private String appVersion = "";
}
