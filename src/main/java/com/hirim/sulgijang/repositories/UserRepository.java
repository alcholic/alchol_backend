package com.hirim.sulgijang.repositories;

import com.hirim.sulgijang.models.User;
import com.hirim.sulgijang.models.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserRepository {
    long test();

    void add(User user);

    void addInfo(UserInfo userInfo);

    UserInfo selectInfo(long userId);

    User select(@Param("snsType") String snsType, @Param("snsId") String snsId);

    User selectByUserId(long userId);

    void deleteInfo(long userId);

    void updatePushToken(@Param("userId") long userId, @Param("pushToken") String pushToken);
}
