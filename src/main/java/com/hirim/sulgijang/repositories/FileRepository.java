package com.hirim.sulgijang.repositories;

import com.hirim.sulgijang.models.Image;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FileRepository {
    void insertFile(Image image);
}
