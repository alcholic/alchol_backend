package com.hirim.sulgijang.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo extends BaseModel {
    private long id;
    private long userId;
    private String deviceId;
    private String pushToken;
    private String accessToken;
    private String deviceType;
    private String appVersion;

    public UserInfo(UserAgent agent, String accessToken) {
        this.deviceId = agent.getDeviceId();
        this.deviceType = agent.getDeviceType();
        this.appVersion = agent.getAppVersion();
        this.accessToken = accessToken;
    }
}
