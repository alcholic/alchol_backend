package com.hirim.sulgijang.services;

import com.hirim.sulgijang.models.User;
import com.hirim.sulgijang.models.UserInfo;
import com.hirim.sulgijang.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User select(String snsType, String snsId) {
        return userRepository.select(snsType, snsId);
    }

    public UserInfo selectInfo(long userId) {
        return userRepository.selectInfo(userId);
    }

    public boolean isAuth(long userId, String accessToken) {
        return Optional.ofNullable(userRepository.selectInfo(userId))
                .filter( user -> user.getAccessToken().equals(accessToken))
                .map( user -> true)
                .orElse(false);
    }

    public String addInfo(long userId, String deviceId, String deviceType, String appVersion, String accessToken) {
        userRepository.deleteInfo(userId);
        userRepository.addInfo(UserInfo.builder()
                .userId(userId)
                .deviceId(deviceId)
                .deviceType(deviceType)
                .appVersion(appVersion)
                .accessToken(accessToken)
                .build());

        return accessToken;
    }

    public long add(User user) {
        userRepository.add(user);
        return user.getUserId();
    }

    public void updatePushToken(long userId, String pushToken) {
        userRepository.updatePushToken(userId, pushToken);
    }
}
